package com.example.instagramparody.repository;

import com.example.instagramparody.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByOrderByCreatedAtDesc();

    List<Post> findByUserIdContains(String userId);
}
