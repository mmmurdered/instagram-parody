package com.example.instagramparody.controller;

import com.example.instagramparody.dto.UserDTO;
import com.example.instagramparody.form.LoginForm;
import com.example.instagramparody.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(authenticationService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm){
        return ResponseEntity.ok(authenticationService.login(loginForm));
    }
}
