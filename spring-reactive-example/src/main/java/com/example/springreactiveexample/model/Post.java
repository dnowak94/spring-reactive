package com.example.springreactiveexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Post {
    @Id
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @CreationTimestamp
    private LocalDateTime timestamp;
}


