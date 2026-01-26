package com.example.Blogapp.service;

import com.example.Blogapp.DTO.CommentDto;

import java.util.List;

public interface commentService {
       CommentDto createPost(Long postId,CommentDto comments);

       List<CommentDto> getAllComments(Long postId);

       CommentDto getCommentById(Long postId,Long commentId);

       CommentDto updateComment(Long postId,Long commentId,CommentDto comment);

       CommentDto deleteComment(Long postId,Long commentId);
}
