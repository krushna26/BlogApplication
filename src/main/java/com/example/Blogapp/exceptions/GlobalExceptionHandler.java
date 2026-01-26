package com.example.Blogapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Resource not found"
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIExceptions.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIExceptions(
            BlogAPIExceptions ex) {

        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Comment is Empty for this post"
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
