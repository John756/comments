package org.example.prispevky;

/**
 * The `Constants` class holds a collection of constant values that are used throughout the application.
 * These constants represent URLs that are frequently accessed, ensuring that they are centralized
 * and easily maintainable.
 */
public class Constants {

    /**
     * The base URL for accessing the application's comments API.
     * This URL is used within the application to interact with the comments resources.
     */
    public static final String BASE_URL = "http://localhost:8081/api/comments";
    /**
     * The URL for accessing the external user API.
     * This URL points to a placeholder service that provides user data. It is used to verify
     * the existence of a user by their ID within the application.
     */
    public static final String USER_API_URL = "https://jsonplaceholder.typicode.com/users/";
    /**
     * The URL for accessing the external comments API.
     * This URL points to a placeholder service that provides comment data. It is used to retrieve
     * comments from an external source, allowing the application to fetch and process these comments.
     */
    public static final  String COMMENTS_API_URL = "https://jsonplaceholder.typicode.com/comments/";
}
