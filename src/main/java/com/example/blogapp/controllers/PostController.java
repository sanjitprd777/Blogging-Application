package com.example.blogapp.controllers;

import com.example.blogapp.config.Constants;
import com.example.blogapp.entities.Post;
import com.example.blogapp.payloads.ApiResponse;
import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.payloads.PostResponse;
import com.example.blogapp.repositories.PostRepo;
import com.example.blogapp.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private final PostService postService;

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

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("postId") Integer id) {
        PostDto post = this.postService.updatePost(postDto, id);
        return new ResponseEntity<>(post, HttpStatus.OK);
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

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByKeywords(@PathVariable("keywords") String keywords) {
        List<PostDto> posts = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Integer id) {
        this.postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/posts/paginated")
    public ResponseEntity<PostResponse> getPostByPage(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE, required = false) Integer pageNo,
            @RequestParam(value = "size", defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {

        PostResponse postByPage = this.postService.getPostByPage(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postByPage, HttpStatus.OK);
    }
}
