package org.example.prispevky.service;

import org.example.prispevky.Constants;
import org.example.prispevky.exception.CommentIdNotFoundException;
import org.example.prispevky.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.example.prispevky.entity.Comment;
import org.example.prispevky.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    /** Injecting the CommentRepository for database operations */
    @Autowired
    private CommentRepository commentRepository;

    /** Injecting a WebClient.Builder for making HTTP requests */
    @Autowired
    private WebClient.Builder webClientBuilder;


    /**
     * Retrieves all comments from the database.
     *
     * @return a list of all comments
     */
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Retrieves a specific comment by its ID. If the comment is not found in the local database,
     * it attempts to retrieve it from an external API. If found, the comment is saved to the local database.
     *
     * @param id the ID of the comment to retrieve
     * @return an Optional containing the found comment, or empty if not found
     */
    public Optional<Comment> getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isEmpty()) {
            try {
                WebClient webClient = webClientBuilder.baseUrl(Constants.COMMENTS_API_URL).build();
                Comment externalComment = webClient.get()
                        .uri("/{id}", id)
                        .retrieve()
                        .bodyToMono(Comment.class)
                        .block(); // Blocking the request to wait for the result
                if (externalComment != null) {
                    commentRepository.save(externalComment);
                    return Optional.of(externalComment);
                }
            } catch (WebClientResponseException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new CommentIdNotFoundException("Comment ID " + id + " does not exist.");
                } else {
                    throw new RuntimeException("Error verifying User ID: " + id);
                }
            }
        }

        return comment;
    }

    /**
     * Retrieves all comments associated with a specific user ID.
     *
     * @param userId the ID of the user whose comments to retrieve
     * @return a list of comments associated with the given user ID
     */
    public List<Comment> getCommentsByUserId(Integer userId) {
        return commentRepository.findByUserId(userId);
    }

    /**
     * Creates a new comment. The user's ID is verified before saving the comment to the database.
     *
     * @param comment the comment to create
     * @return the created comment
     */
    @Transactional
    public Comment createComment(Comment comment) {
        verifyUserId(comment.getUserId());
        return commentRepository.save(comment);
    }

    /**
     * Updates an existing comment with new details. The comment is retrieved by ID,
     * and its title and body are updated with the new values.
     *
     * @param id the ID of the comment to update
     * @param commentDetails the new details for the comment
     * @return the updated comment
     */
    @Transactional
    public Comment updateComment(Integer id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setTitle(commentDetails.getTitle());
        comment.setBody(commentDetails.getBody());
        return commentRepository.save(comment);
    }

    /**
     * Deletes a comment by its ID. The comment is retrieved by ID, and if found, it is deleted from the database.
     *
     * @param id the ID of the comment to delete
     */
    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        commentRepository.delete(comment);
    }

    /**
     * Verifies that a user with the given ID exists by making a request to an external User API.
     * If the user does not exist, an exception is thrown.
     *
     * @param userId the ID of the user to verify
     */
    private void verifyUserId(Integer userId) {
        WebClient webClient = webClientBuilder.baseUrl(Constants.USER_API_URL).build();

        try {
            webClient.get()
                    .uri("/{id}", userId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // Blocking the request
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User with ID " + userId + " does not exist.");
            } else {
                throw new RuntimeException("Error verifying User ID: " + userId);
            }
        }
    }
}
