package com.example.Blogapp.service;

import com.example.Blogapp.DTO.CommentDto;
import com.example.Blogapp.entity.Comment;
import com.example.Blogapp.entity.Posts;
import com.example.Blogapp.exceptions.BlogAPIExceptions;
import com.example.Blogapp.exceptions.ResourceNotFoundException;
import com.example.Blogapp.mapper.mapDtoToEntity;
import com.example.Blogapp.mapper.mapEntityToDto;
import com.example.Blogapp.repository.commentsRepository;
import com.example.Blogapp.repository.postsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class commentServiceImpl implements  commentService {
    private commentsRepository commentRepos;
    private mapDtoToEntity maptoEntity;

    private mapEntityToDto maptoDto;


    private postsRepository postRepos;

    public commentServiceImpl(postsRepository postRepos,commentsRepository commentRepos, mapDtoToEntity maptoEntity, mapEntityToDto maptoDto) {
        this.commentRepos = commentRepos;
        this.maptoEntity = maptoEntity;
        this.maptoDto = maptoDto;
        this.postRepos=postRepos;
    }

    @Override
    public CommentDto createPost(Long postId,CommentDto comments) {
        Comment comment=maptoEntity.mapCommentDtoToEntity(comments);

        Posts post=postRepos.findById(postId).orElseThrow(()->
            new ResourceNotFoundException("Post","ID",postId)
        );
        comment.setPost(post);
        Comment commentSaved=commentRepos.save(comment);

        return maptoDto.mapEntityToCommentDto(commentSaved);

    }

    @Override
    public List<CommentDto> getAllComments(Long postId) {
        Posts post=postRepos.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","ID",postId)
        );

        List<Comment>comments=commentRepos.findAllByPost_id(postId);

        return comments.stream()
                .map(comment -> maptoDto.mapEntityToCommentDto(comment))
                .collect(Collectors.toList());


    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        Posts post=postRepos.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","ID",postId)
        );

        Comment comment=commentRepos.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("comment","ID",commentId)
        );

        if (!(post.getId()==comment.getPost().getId())){
            throw  new BlogAPIExceptions("comment","ID",commentId);

        }
        return maptoDto.mapEntityToCommentDto(comment);



    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto comment) {


        Posts post=postRepos.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","ID",postId)
        );

        Comment comments=commentRepos.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("comment","ID",commentId)
        );

        if (!(post.getId()==comments.getPost().getId())){
            throw  new BlogAPIExceptions("comment","ID",commentId);

        }

        comments.setBody(comment.getBody());
        comments.setEmail(comment.getEmail());
        comments.setName(comment.getName());

        Comment upComment=commentRepos.save(comments);
        return maptoDto.mapEntityToCommentDto(upComment);

    }

    @Override
    @Transactional
    public CommentDto deleteComment(Long postId, Long commentId) {

        Posts post=postRepos.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","ID",postId)
        );

        Comment comments=commentRepos.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("comment","ID",commentId)
        );

        if (!(post.getId()==comments.getPost().getId())){
            throw  new BlogAPIExceptions("comment","ID",commentId);

        }

        commentRepos.deleteByIdAndPostId(postId,commentId);
        return maptoDto.mapEntityToCommentDto(comments);


    }


}
