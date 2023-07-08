package com.example.instagramparody.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {

    @Size(min = 1, max = 256)
    private String comment;

}
