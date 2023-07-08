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

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginForm loginForm){

        //TODO troubles with this shit
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

        User user = userRepository.findUserByUsername(loginForm.getUsername()).orElseThrow();
        System.out.println("User:" + user);

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
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
