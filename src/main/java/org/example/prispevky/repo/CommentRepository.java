package org.example.prispevky.repo;

import java.util.List;

import org.example.prispevky.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for {@link Comment} entities.
 *
 * This interface extends {@link JpaRepository} to provide CRUD operations and additional query methods
 * for {@link Comment} entities. It uses the primary key type of {@link Integer}.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Retrieves a list of comments associated with a specific user ID.
     *
     * @param userId the ID of the user whose comments are to be retrieved.
     * @return a list of {@link Comment} objects associated with the given user ID.
     */
    List<Comment> findByUserId(Integer userId);

}