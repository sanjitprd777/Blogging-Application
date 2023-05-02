package com.example.blogapp.payloads;

import com.example.blogapp.entities.Category;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
