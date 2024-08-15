package org.example.prispevky;
import org.example.prispevky.entity.Comment;
import org.example.prispevky.repo.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CommentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void testGetAllComments() {
        ResponseEntity<List> response = restTemplate.getForEntity(Constants.BASE_URL, List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void testGetCommentById() {
        ResponseEntity<Comment> response = restTemplate.getForEntity(Constants.BASE_URL + "/1", Comment.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
    }

    @Test
    public void testGetCommentByIdNotFound() {
        ResponseEntity<Map> response = restTemplate.getForEntity(Constants.BASE_URL + "/999999", Map.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        // Extract and assert validation errors
        Map<String, String> errors = response.getBody();
        assertThat(errors).isNotNull();
        assertThat(errors.get("error")).isEqualTo("Comment ID 999999 does not exist.");
    }

    @Test
    public void testGetCommentsByUserId() {
        ResponseEntity<List> response = restTemplate.getForEntity(Constants.BASE_URL + "/user/1", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void testCreateComment_Successful() {
        // Given
        Comment comment = new Comment();
        comment.setUserId(1);  // Assuming this USERID is valid in the external API
        comment.setTitle("Test Title");
        comment.setBody("Test Body");

        // When
        ResponseEntity<Comment> response = restTemplate.postForEntity(Constants.BASE_URL, comment, Comment.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull(); // The ID should be set by the database
        assertThat(response.getBody().getUserId()).isEqualTo(1);
        assertThat(response.getBody().getTitle()).isEqualTo("Test Title");
        assertThat(response.getBody().getBody()).isEqualTo("Test Body");
    }

    @Test
    public void testCreateComment_InvalidUserId() {
        // Given
        Comment comment = new Comment();
        comment.setUserId(99999);  // Assuming this USERID does not exist in the external API
        comment.setTitle("Test Title");
        comment.setBody("Test Body");

        // When
        ResponseEntity<String> response = restTemplate.postForEntity(Constants.BASE_URL, comment, String.class);

        // Then
        assertThat(response.getBody()).contains("User with ID 99999 does not exist.");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void testUpdateComment() {
        Comment updatedComment = new Comment();
        updatedComment.setUserId(1);
        updatedComment.setTitle("Updated Comment");
        updatedComment.setBody("This comment has been updated.");

        HttpEntity<Comment> requestUpdate = new HttpEntity<>(updatedComment);
        ResponseEntity<Comment> response = restTemplate.exchange(Constants.BASE_URL + "/1", HttpMethod.PUT, requestUpdate, Comment.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle()).isEqualTo("Updated Comment");
        assertThat(response.getBody().getBody()).isEqualTo("This comment has been updated.");
    }

    @Test
    public void testDeleteComment() {
        // Separate comment for deletion in order to run tests without falling
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setTitle("To Be Deleted");
        comment.setBody("This comment will be deleted.");

        // Save the comment to the database
        ResponseEntity<Comment> createResponse = restTemplate.postForEntity(Constants.BASE_URL, comment, Comment.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Integer commentId = createResponse.getBody().getId();

        // When
        restTemplate.delete(Constants.BASE_URL + "/" + commentId);

        // Then
        Optional<Comment> deletedComment = commentRepository.findById(commentId);
        assertThat(deletedComment).isEmpty();
    }

    @Test
    public void testUpdateCommentsUserIdNotPossible() {
        // Create a new comment to update
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setTitle("Original Title");
        comment.setBody("Original Body");

        // Save the comment to the database
        ResponseEntity<Comment> createResponse = restTemplate.postForEntity("/api/comments", comment, Comment.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Integer commentId = createResponse.getBody().getId();

        // Prepare the update all atributes
        comment.setId(111);
        comment.setUserId(111);
        comment.setTitle("Updated Title");
        comment.setBody("Updated Body");

        // Send PUT request to update the comment
        HttpEntity<Comment> requestUpdate = new HttpEntity<>(comment);
        ResponseEntity<Comment> updateResponse = restTemplate.exchange("/api/comments/" + commentId, HttpMethod.PUT, requestUpdate, Comment.class);

        // Verify the that it is not possible to change id and userId, update was successful only Title Body
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Comment updatedComment = updateResponse.getBody();
        assertThat(updatedComment.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedComment.getBody()).isEqualTo("Updated Body");
        assertThat(updatedComment.getId() != 111); // Ensure userId hasn't changed
        assertThat(updatedComment.getUserId() != 111);  // Ensure userId hasn't changed
        assertThat(updatedComment.getId()).isEqualTo(commentId);  // Ensure id hasn't changed
    }

    @Test
    public void whenValidationFails_thenBadRequestAndValidationErrorsReturned() {
        // Create an invalid Comment object (e.g. missing required fields)
        Comment invalidComment = new Comment();
        invalidComment.setUserId(1);
        invalidComment.setBody("**Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body****Too long Body Too long Body**");
        // Leave fields unset to trigger validation errors

        // Send POST request to the /api/comments endpoint
        ResponseEntity<Map> response = restTemplate.postForEntity(Constants.BASE_URL, invalidComment, Map.class);

        // Assert that the status code is BAD_REQUEST (400)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // Extract and assert validation errors
        Map<String, String> errors = response.getBody();
        assertThat(errors).isNotNull();
        assertThat(errors.get("body")).isEqualTo("Body must be between 1 and 255 characters.");
    }
}
