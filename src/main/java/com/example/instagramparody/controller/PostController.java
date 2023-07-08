package com.example.instagramparody.controller;

import com.example.instagramparody.dto.PostDTO;
import com.example.instagramparody.entity.Comment;
import com.example.instagramparody.entity.Post;
import com.example.instagramparody.entity.User;
import com.example.instagramparody.repository.PostRepository;
import com.example.instagramparody.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid PostDTO postDTO, Authentication authentication) throws IOException {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        Post post = Post.builder()
                .content(postDTO.getContent())
                .createdAt(LocalDateTime.now())
                .likes(0)
                .userId(user.getId())
                .comments(new ArrayList<>())
                .build();

        user.getUserPosts().add(post);
        userRepository.save(user);
        postRepository.save(post);

        return ResponseEntity.ok("Was posted successfully");
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<?> likePost(@PathVariable String postId, Authentication authentication) {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();

        if (!user.getLikedPosts().contains(post)) {
            post.setLikes(post.getLikes() + 1);
            user.getLikedPosts().add(post);
        } else {
            user.getLikedPosts().remove(post);
            post.setLikes(post.getLikes() - 1);
        }

        userRepository.save(user);
        postRepository.save(post);

        return ResponseEntity.ok("Post " + postId + " was rated successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPostsDesc() {
        List<Post> postList = postRepository.findByOrderByCreatedAtDesc();
        return ResponseEntity.ok(postList);
    }

    @GetMapping("/followed")
    public ResponseEntity<?> getAllFollowedPostsDesc(Authentication authentication) {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        List<Post> postList = postRepository.findByOrderByCreatedAtDesc();
        List<Post> followedPosts = new ArrayList<>();
        for(User tempUser : user.getFollows()){
            followedPosts.addAll(postRepository.findByUserIdContains(tempUser.getId()));
        }
        return ResponseEntity.ok(postList);
    }
}
