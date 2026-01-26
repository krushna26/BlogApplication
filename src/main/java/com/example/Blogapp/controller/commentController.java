package com.example.Blogapp.controller;


import com.example.Blogapp.DTO.CommentDto;
import com.example.Blogapp.service.commentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/comments")
public class commentController {

    private commentServiceImpl commentService;

    public commentController(commentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/create")
    public ResponseEntity<CommentDto>createComment(@PathVariable Long postId, @RequestBody CommentDto comment){


        System.out.println(comment.getBody());
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(commentService.createPost(postId,comment));



    }


    @GetMapping("/{postId}/getAll")

    public ResponseEntity<List<CommentDto>> getAllComment( @PathVariable Long postId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllComments(postId));

    }



    @GetMapping("/{postId}/get/{commentId}")
    public ResponseEntity<CommentDto>getCommentById(@PathVariable Long postId,@PathVariable Long commentId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getCommentById(postId,commentId));
    }

    @PutMapping("/{postId}/update/{commentId}")
    public ResponseEntity<Map<String,Object>>updateComment(@PathVariable Long postId,
                                                             @PathVariable Long commentId,
                                                             @RequestBody CommentDto comment
                                                             ){
        Map<String,Object>map=new HashMap<>();
        CommentDto updatedComment=commentService.updateComment(postId,commentId,comment);
        map.put("Message","Updated");
        map.put("comment",updatedComment);


        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(map
                        );
    }

    @DeleteMapping("/{postId}/delete/{commentId}")
    public ResponseEntity<Map<String,Object>>deleteComment(@PathVariable Long postId,
                                                           @PathVariable Long commentId
    ){
        Map<String,Object>map=new HashMap<>();
        CommentDto deletedComment=commentService.deleteComment(postId,commentId);
        map.put("Message","Deleted");
        map.put("comment",deletedComment);


        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(map
                        );
    }

}
