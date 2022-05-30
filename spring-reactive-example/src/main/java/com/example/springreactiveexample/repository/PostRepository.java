package com.example.springreactiveexample.repository;

import com.example.springreactiveexample.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
public interface PostRepository extends ReactiveCrudRepository<Post, Long>{
}
