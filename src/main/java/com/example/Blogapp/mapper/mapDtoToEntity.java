package com.example.Blogapp.mapper;

import com.example.Blogapp.DTO.CommentDto;
import com.example.Blogapp.DTO.PostDto;
import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.entity.Posts;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class mapDtoToEntity {
    private ModelMapper mapper;

    public mapDtoToEntity(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Posts mapPostDtoToEntity(PostDto postdto){
        Posts post=mapper.map(postdto,Posts.class);
        return post;
    }


    public Comment mapCommentDtoToEntity(CommentDto comments){
        Comment comment=mapper.map(comments,Comment.class);;
        return comment;

    }


}
