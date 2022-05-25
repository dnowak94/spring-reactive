package com.example.springexample.repository;

import com.example.springexample.model.Post;
import org.springframework.data.repository.CrudRepository;
public interface PostRepository extends CrudRepository<Post, Long> {
}