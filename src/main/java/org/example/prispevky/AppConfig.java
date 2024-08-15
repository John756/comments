package org.example.prispevky;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /**
     * Defines a `RestTemplate` bean that can be used for making RESTful web service calls.
     *
     * The `RestTemplate` is a synchronous client to perform HTTP requests,
     * exposing a simple, template method API over underlying HTTP client libraries.
     *
     * By declaring this method with the `@Bean` annotation, Spring will automatically manage
     * the lifecycle of the `RestTemplate` instance, making it available for injection into
     * other components of the application where it is needed.
     *
     * @return a new instance of `RestTemplate`
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}