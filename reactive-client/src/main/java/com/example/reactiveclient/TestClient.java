package com.example.reactiveclient;

import com.example.reactiveclient.model.Post;
import com.example.reactiveclient.model.PostRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
public class TestClient {
    private final WebClient client;

    private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

    public TestClient() {
        client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeaders(headers -> {
                    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
                    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                }).build();
    }

    public Flux<Post> getAllPosts() {
        logger.info("Getting all posts...");
        return client.get()
                .uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Post.class);
    }

    public Mono<Post> getPostById(long id) {
        logger.info("getting Post with id = " + id + "...");
        long startTime = System.nanoTime();
        return client.get()
                .uri("/posts/"+id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException(error.toString())))
                .bodyToMono(Post.class);
    }

    public Mono<ClientResponse> create() {
        logger.info("creating a new post...");
        var post = new PostRequest("Post", "This is a post.");
        long startTime = System.nanoTime();
        return client.post()
                .uri("/posts")
                .bodyValue(post)
                .exchangeToMono(resp ->Mono.just(resp));
    }
}
