package com.example.Blogapp.mapper;

import com.example.Blogapp.DTO.CommentDto;
import com.example.Blogapp.DTO.PostDto;
import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.entity.Posts;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class mapEntityToDto {
    private ModelMapper mapper;

    public mapEntityToDto(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PostDto postEntitytoDto(Posts post){
        PostDto postdto=mapper.map(post,PostDto.class);
        return postdto;

    }

    public CommentDto mapEntityToCommentDto(Comment comments){
        CommentDto comment=mapper.map(comments,CommentDto.class);
        return comment;

    }

}
