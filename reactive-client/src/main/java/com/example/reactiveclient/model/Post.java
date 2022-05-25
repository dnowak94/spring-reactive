package com.example.reactiveclient.model;

import com.example.reactiveclient.model.PostRequest;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post extends PostRequest {
    private long id;
    private LocalDateTime timestamp;

    public Post() {
        super();
    }
    public Post(long id, String title, String content, LocalDateTime timestamp) {
        super(title, content);
        this.id = id;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                " id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
