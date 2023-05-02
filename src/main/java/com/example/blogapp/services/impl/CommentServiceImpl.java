package com.example.blogapp.services.impl;

import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.Post;
import com.example.blogapp.exceptions.ResourceNotFoundException;
import com.example.blogapp.payloads.CommentDto;
import com.example.blogapp.repositories.CommentRepo;
import com.example.blogapp.repositories.PostRepo;
import com.example.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private PostRepo postRepo;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo, ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        // Fetch post
        Post post = this.postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(post.getUser());

        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        this.commentRepo.delete(comment);
    }
}
