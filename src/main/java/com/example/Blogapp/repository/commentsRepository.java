package com.example.Blogapp.repository;

import com.example.Blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface commentsRepository extends JpaRepository< Comment,Long> {

    List<Comment> findAllByPost_id(Long PostId);

    void deleteByIdAndPostId(Long commentId, Long postId);


}
