package org.example.prispevky;

import org.example.prispevky.controller.CommentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PrispevkyApplicationTests {

    @Autowired
    private CommentController controller;

    @Test
    void
    contextLoads() {
        assertThat(controller).isNotNull();
    }

}
