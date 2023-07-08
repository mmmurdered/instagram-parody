package com.example.instagramparody.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private String userId;

    private String content;

    private Binary picture;

    private Integer likes;

    private LocalDateTime createdAt;

    private List<Comment> comments;
}
