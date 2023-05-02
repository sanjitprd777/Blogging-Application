package com.example.blogapp.controllers;

import com.example.blogapp.entities.Comment;
import com.example.blogapp.payloads.ApiResponse;
import com.example.blogapp.payloads.CommentDto;
import com.example.blogapp.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,
                                                    @PathVariable("postId") Integer id) {
        CommentDto comment1 = this.commentService.createComment(comment, id);
        return new ResponseEntity<>(comment1, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer id) {
        this.commentService.deleteComment(id);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }

}
