package com.example.blogapp.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Integer id;

    @NotBlank
    private String content;

    private UserDto user;
}
