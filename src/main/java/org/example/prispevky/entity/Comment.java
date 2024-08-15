package org.example.prispevky.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    /**
     * Unique identifier for the comment.
     * This field is the primary key and is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ID of the user who created the comment.
     * This field is nullable, but must be greater than or equal to 1 if provided.
     */
    @Column(name = "USERID", nullable = true)
    @Min(value = 1, message = "User ID must be greater than or equal to 1")
    private Integer userId;

    /**
     * Title of the comment.
     * Not restricted, external API returns also blank title
     */
    private String title;
    @Size(min = 1, max = 255, message = "Body must be between 1 and 255 characters.")
    private String body;
}