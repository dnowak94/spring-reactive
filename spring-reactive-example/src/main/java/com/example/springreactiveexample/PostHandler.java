package com.example.springreactiveexample;
import com.example.springreactiveexample.model.Post;
import com.example.springreactiveexample.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
class PostHandler {
    private static final Logger logger = LoggerFactory.getLogger(PostHandler.class);

    private final PostRepository repo;

    public PostHandler(PostRepository repo) {
        this.repo = repo;
    }

    public Mono<ServerResponse> listPosts(ServerRequest req) {
        Flux<Post> posts = repo.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(posts, Post.class);
    }

    public Mono<ServerResponse> createPost(ServerRequest req) {
        return req.bodyToMono(Post.class)
            .flatMap(repo::save)
            .flatMap(p -> ServerResponse.created(URI.create(req.uri().toString() + p.getId())).bodyValue(p));
    }

    public Mono<ServerResponse> getPost(ServerRequest req) {
        logger.info("getPost("+ req.pathVariable("id")+")");
        return repo.findById(Long.valueOf(req.pathVariable("id")))
                .flatMap(post -> ServerResponse.ok().contentType(APPLICATION_JSON).body(Mono.just(post),Post.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        return repo.findById(Long.valueOf(req.pathVariable("id")))
                .flatMap(post -> ServerResponse.ok().build(repo.delete(post)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}