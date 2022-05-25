package com.example.springreactiveexample.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Post implements Serializable {
    @Id
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @CreationTimestamp
    private LocalDateTime timestamp;
}


