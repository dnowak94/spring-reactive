package com.example.springreactiveexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Configuration
public class PostRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> route(PostHandler postHandler) {
        return RouterFunctions
                .route()
                .GET("/posts", accept(MediaType.APPLICATION_JSON), postHandler::listPosts)
                .POST("/posts", contentType(MediaType.APPLICATION_JSON), postHandler::createPost)
                .GET("/posts/{id}",accept(MediaType.TEXT_PLAIN), postHandler::getPost)
                .build();
    }
}