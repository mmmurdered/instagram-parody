package com.example.instagramparody.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    @Size(min = 6, max = 32)
    private String username;

    @Size(min = 6, max = 32)
    private String password;

    @Email
    private String email;
}
