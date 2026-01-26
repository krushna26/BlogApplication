package com.example.Blogapp.repository;

import com.example.Blogapp.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postsRepository extends JpaRepository<Posts,Long> {
}
