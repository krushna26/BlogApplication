package com.example.Blogapp.service;


import com.example.Blogapp.DTO.PostDto;

import java.util.List;

public interface postService {
    PostDto createPost(PostDto postdto);
    List<PostDto> getAllPosts();
    PostDto getPostbyId(Long id);

    PostDto updatePost(Long id,PostDto Data);


    void  deletePostById(Long id);
}
