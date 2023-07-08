package com.example.instagramparody.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {

    @Size(min = 1, max = 2048)
    private String content;

}
