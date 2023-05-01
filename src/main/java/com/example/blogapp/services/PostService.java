package com.example.blogapp.services;

import com.example.blogapp.entities.Post;
import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer id);

    void deletePost(Integer id);

    PostDto getPostById(Integer id);

    List<PostDto> getAllPost();

    PostResponse getPostByPage(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
