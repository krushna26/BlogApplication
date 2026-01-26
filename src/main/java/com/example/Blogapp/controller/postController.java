package com.example.Blogapp.controller;

import com.example.Blogapp.DTO.PostDto;
import com.example.Blogapp.service.postServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class postController {
    private postServiceImpl postService;

    public postController(postServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postData){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.createPost(postData));

    }

    @GetMapping
    public ResponseEntity<List<PostDto>>getAllPosts(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getAllPosts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostbyId(@PathVariable Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.getPostbyId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable Long id,@RequestBody PostDto postdto){
        Map<String, Object> response=new HashMap<>();
        response.put("message", "Post updated successfully");
        PostDto updatedPost=postService.updatePost(id,postdto);
        response.put("updatedPost",updatedPost);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>>deletePost(@PathVariable Long id){
        postService.deletePostById(id);
        Map<String,String>response=new HashMap<>();
        response.put("Message","Post deleted with id:" + id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
