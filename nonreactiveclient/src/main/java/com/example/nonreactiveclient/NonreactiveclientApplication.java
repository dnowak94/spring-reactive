package com.example.nonreactiveclient;

import com.example.nonreactiveclient.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class NonreactiveclientApplication {
    private static final Logger log = LoggerFactory.getLogger(NonreactiveclientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NonreactiveclientApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(TestClient client) throws Exception {
        return args -> {
            Post createdPost = client.create();
            Post postById = client.getById(createdPost.getId());
            List<Post> posts = client.getAll();
        };
    }
}
