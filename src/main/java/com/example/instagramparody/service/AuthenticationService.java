package com.example.instagramparody.service;

import com.example.instagramparody.dto.UserDTO;
import com.example.instagramparody.entity.User;
import com.example.instagramparody.form.LoginForm;
import com.example.instagramparody.repository.UserRepository;
import com.example.instagramparody.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginForm loginForm){

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        User user = userRepository.findUserByUsername(loginForm.getUsername()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(UserDTO userDTO){
        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .followers(new ArrayList<>())
                .userPosts(new ArrayList<>())
                .likedPosts(new ArrayList<>())
                .follows(new ArrayList<>())
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
