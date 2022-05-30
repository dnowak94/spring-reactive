package com.example.nonreactiveclient;

import com.example.nonreactiveclient.model.Post;
import com.example.nonreactiveclient.model.PostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class TestClient {
    private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

    final String BASE_URL = "http://localhost:8080/posts";
    RestTemplate client;

    public TestClient() {
        client = new RestTemplate();
    }

    public List<Post> getAll() {
        long startTime = System.nanoTime();
        ResponseEntity<List> response = client.getForEntity(BASE_URL, List.class);
        long duration = Utils.calcDuration(startTime);
        List<Post> posts = response.getBody();
        logger.info(posts.toString());
        logger.info("duration: " + Utils.durationToString(duration));
        return posts;
    }

    public Post getById(long id) {
        long startTime = System.nanoTime();
        ResponseEntity<Post> response = client.getForEntity(BASE_URL + "/{id}", Post.class, id);
        long duration = Utils.calcDuration(startTime);
        logger.info("getById({}: {}", id, response.getBody());
        logger.info("duration: " + Utils.durationToString(duration));
        return response.getBody();
    }

    public Post create() {
        PostRequest post = new PostRequest("Post", "This is a post.");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<PostRequest> request = new HttpEntity<>(post, headers);
        long startTime = System.nanoTime();
        Post createdPost = client.postForObject(BASE_URL, request, Post.class);
        long duration = Utils.calcDuration(startTime);
        logger.info("Created: " + createdPost.toString());
        logger.info("duration: " + Utils.durationToString(duration));
        return createdPost;
    }
}
