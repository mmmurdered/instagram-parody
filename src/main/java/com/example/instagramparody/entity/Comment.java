package com.example.instagramparody.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Comment {

    @Id
    private String id;

    private String userId;

    private String content;
}
