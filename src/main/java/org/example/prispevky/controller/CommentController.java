package org.example.prispevky.controller;

import org.example.prispevky.entity.Comment;
import org.example.prispevky.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing comments.
 * This controller provides endpoints for CRUD operations on comments.
 * It uses {@link CommentService} to interact with the data layer.
 * The API is exposed under the path `/api/comments`.
 *
 * <p>Note: This controller is annotated with {@link Validated}, which means
 * that method parameters and return values are subject to validation.</p>
 *
 * @author Your Name
 */
@RestController
@RequestMapping("/api/comments")
@Validated
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves all comments.
     *
     * @return a list of all {@link Comment} objects.
     */
    @GetMapping
    public List<Comment> getAllComments() {
        List<Comment> allComments = commentService.getAllComments();
        return allComments;
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment to retrieve.
     * @return a {@link ResponseEntity} containing the {@link Comment} if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves comments by user ID.
     *
     * @param userId the ID of the user whose comments are to be retrieved.
     * @return a {@link ResponseEntity} containing a list of {@link Comment} objects,
     *         or a 204 status if no comments are found.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable Integer userId) {
        List<Comment> comments = commentService.getCommentsByUserId(userId);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(comments);
        }
    }

    /**
     * Creates a new comment.
     *
     * @param comment the {@link Comment} object to create.
     * @return a {@link ResponseEntity} containing the created {@link Comment}.
     */
    @PostMapping
    public ResponseEntity<?> createComment(@Valid @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }

    /**
     * Updates an existing comment.
     *
     * @param id the ID of the comment to update.
     * @param comment the {@link Comment} object containing updated data.
     * @return a {@link ResponseEntity} containing the updated {@link Comment}.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id, @Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(id, comment));
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id the ID of the comment to delete.
     * @return a {@link ResponseEntity} with a 204 status indicating that the comment was successfully deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
