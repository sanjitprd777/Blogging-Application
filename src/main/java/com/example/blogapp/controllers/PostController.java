package com.example.blogapp.controllers;

import com.example.blogapp.entities.Post;
import com.example.blogapp.payloads.ApiResponse;
import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.repositories.PostRepo;
import com.example.blogapp.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Integer uId,
                                              @PathVariable("categoryId") Integer cId) {
        PostDto post = this.postService.createPost(postDto, uId, cId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer id) {
        List<PostDto> postsByUser = this.postService.getPostsByUser(id);
        return new ResponseEntity<>(postsByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer id) {
        List<PostDto> postsByCategory = this.postService.getPostsByCategory(id);
        return new ResponseEntity<>(postsByCategory, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<PostDto> posts = this.postService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer id) {
        PostDto post = this.postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Integer id) {
        this.postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }
}
