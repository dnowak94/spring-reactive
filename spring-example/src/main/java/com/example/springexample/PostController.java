package com.example.springexample;

import com.example.springexample.model.Post;
import com.example.springexample.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping(path="/posts")
public class PostController {
    @Autowired
    private PostRepository repo;

    @GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Post> all() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> get(@PathVariable(value = "id") Long id) {
        Optional<Post> post = this.repo.findById(id);
        if(post == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(post.get());
        }
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> create(@RequestBody Post post) {
        System.out.println(post);
        Post newPost = new Post(post.getTitle(),post.getContent());
        Post savedPost = this.repo.save(newPost);
        if(savedPost != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedPost.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(savedPost);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
