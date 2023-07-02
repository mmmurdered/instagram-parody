package com.example.instagramparody.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello");
    }
}
