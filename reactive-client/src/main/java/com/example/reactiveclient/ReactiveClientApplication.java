package com.example.reactiveclient;

import com.example.reactiveclient.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.List;

@SpringBootApplication
public class ReactiveClientApplication {
    private static final Logger logger = LoggerFactory.getLogger(ReactiveClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReactiveClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TestClient c) {
        return args -> {
            c.create().subscribe(resp -> logger.info(resp.statusCode().toString()),
                    error -> logger.error(error.getMessage()));
            c.getPostById(20).timeout(Duration.ofSeconds(3)).subscribe(p -> logger.info(p.toString()));
            // List<Post> posts;
            // c.getAllPosts().subscribe(p -> logger.info(p.toString()));
            // c.getAllPosts();
        };
    }
}
