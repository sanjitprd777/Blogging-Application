package com.example.blogapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

}
