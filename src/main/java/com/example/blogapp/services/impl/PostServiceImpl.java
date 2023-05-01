package com.example.blogapp.services.impl;

import com.example.blogapp.entities.Category;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.User;
import com.example.blogapp.exceptions.ResourceNotFoundException;
import com.example.blogapp.payloads.PostDto;
import com.example.blogapp.payloads.PostResponse;
import com.example.blogapp.repositories.CategoryRepo;
import com.example.blogapp.repositories.PostRepo;
import com.example.blogapp.repositories.UserRepo;
import com.example.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;
    private UserRepo userRepo;
    private CategoryRepo categoryRepo;
    private ModelMapper mapper;


    public PostServiceImpl(PostRepo postRepo, ModelMapper mapper,
                           UserRepo userRepo, CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId));

        Post post = this.mapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = this.postRepo.save(post);
        return this.mapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());

        Post updatedPost = this.postRepo.save(post);
        return this.mapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return this.mapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();
        return posts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostByPage(Integer page, Integer size, String sortBy, String sortDir) {
        Sort sort = "desc".equalsIgnoreCase(sortDir) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> postPage = this.postRepo.findAll(pageable);
        List<Post> allPosts = postPage.getContent();

        List<PostDto> allPostDtos = allPosts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDtos);
        postResponse.setPageNumber(page);
        postResponse.setPageSize(size);
        postResponse.setTotalElements((int) postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postResponse.isLastPage());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);

        return posts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
        List<Post> posts = this.postRepo.findByUser(user);

        return posts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByContentContaining(keyword);
        return posts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
