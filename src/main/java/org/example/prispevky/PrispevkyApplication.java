package org.example.prispevky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * The main class for the Prispevky application.
 * This class serves as the entry point for the Spring Boot application.
 * It configures and launches the application, including scanning for JPA entities.
 */
@SpringBootApplication
@EntityScan("org.example.prispevky.entity")
public class PrispevkyApplication {

    /**
     * The main method that serves as the entry point for the Java application.
     * This method uses Spring Boot's `SpringApplication.run()` method to launch the application.
     *
     * @param args command-line arguments passed to the application (if any).
     */
    public static void main(String[] args) {
        SpringApplication.run(PrispevkyApplication.class, args);
    }

}
