package com.example.Blogapp.service;

import com.example.Blogapp.DTO.PostDto;
import com.example.Blogapp.entity.Posts;
import com.example.Blogapp.exceptions.ResourceNotFoundException;
import com.example.Blogapp.mapper.mapDtoToEntity;
import com.example.Blogapp.mapper.mapEntityToDto;
import com.example.Blogapp.repository.postsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements  postService{

    private postsRepository postRepos;
    private mapDtoToEntity maptoEntity;

    private mapEntityToDto maptoDto;

    public postServiceImpl(postsRepository postRepos, mapDtoToEntity maptoEntity, mapEntityToDto maptoDto) {
        this.postRepos = postRepos;
        this.maptoEntity = maptoEntity;
        this.maptoDto = maptoDto;
    }


    @Override
    public PostDto createPost(PostDto postdto) {
        Posts post=maptoEntity.mapPostDtoToEntity(postdto);
        Posts posts=postRepos.save(post);
        PostDto postsdto =maptoDto.postEntitytoDto(posts);
        return  postsdto;


    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Posts>posts=postRepos.findAll();
        List<PostDto>postdtos=posts.stream()
                .map((post)->maptoDto.postEntitytoDto(post))
                .collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public PostDto getPostbyId(Long id) {
        Posts post=postRepos.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));
        return maptoDto.postEntitytoDto(post);

    }

    @Override
    public PostDto updatePost(Long id, PostDto data) {
        Posts post=postRepos.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", "id", id));
        post.setTitle(data.getTitle());
        post.setContent(data.getContent());
        post.setDescription(data.getDescription());
        Posts updatedPost=postRepos.save(post);
        return maptoDto.postEntitytoDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
       Posts post=postRepos.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", "id", id));
        postRepos.delete(post);
    }


}
