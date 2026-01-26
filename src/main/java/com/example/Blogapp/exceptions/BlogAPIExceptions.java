package com.example.Blogapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogAPIExceptions extends RuntimeException {
     public BlogAPIExceptions(String resourceName, String fieldName, Long fieldValue) {
          super(resourceName+" does not belong to post ID "+" fieldName "+ fieldValue);

     }

    }

