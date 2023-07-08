package com.example.instagramparody.controller;

import com.example.instagramparody.dto.CommentDTO;
import com.example.instagramparody.entity.Comment;
import com.example.instagramparody.entity.Post;
import com.example.instagramparody.entity.User;
import com.example.instagramparody.repository.PostRepository;
import com.example.instagramparody.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @PostMapping("/follow/{userId}")
    public ResponseEntity<?> followToUser(@PathVariable String userId, Authentication authentication) {
        User currentUser = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        User userToFollow = userRepository.findById(userId).orElseThrow();

        if (!currentUser.getFollows().contains(userToFollow)) {
            currentUser.getFollows().add(userToFollow);
        } else {
            currentUser.getFollows().remove(userToFollow);
        }
        userRepository.save(currentUser);
        return ResponseEntity.ok("User was (un)followed to " + userId + " successfully");
    }

    @PostMapping("/followers")
    public ResponseEntity<?> getFollowers(@PathVariable String userId, Authentication authentication) {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();

        return ResponseEntity.ok(user.getFollowers());
    }

    @PostMapping("/follows")
    public ResponseEntity<?> getFollows(@PathVariable String userId, Authentication authentication) {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();

        return ResponseEntity.ok(user.getFollows());
    }

    @PostMapping("/liked")
    public ResponseEntity<?> getLikedPosts(@PathVariable String userId, Authentication authentication) {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();

        return ResponseEntity.ok(user.getLikedPosts());
    }

    @PostMapping("/comment/{postId}")
    public ResponseEntity<?> commentToPost(@PathVariable String postId, @RequestBody @Valid CommentDTO commentDTO
            , Authentication authentication) {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();

        post.getComments().add(Comment.builder()
                .content(commentDTO.getComment())
                .userId(user.getId())
                .build());

        return ResponseEntity.ok("Post " + postId + " was commented successfully");
    }
}
