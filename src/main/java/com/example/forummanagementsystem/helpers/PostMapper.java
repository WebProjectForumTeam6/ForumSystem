package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.service.contracts.CategoryService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class PostMapper {
    public PostMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private final CategoryService categoryService;

    public Post fromDtoIn(PostDto postDto, User creator) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedBy(creator);
        post.setCreatedAt(LocalDateTime.now());
        post.setCategory(categoryService.getById(postDto.getCategoryId()));
        post.setTags(new HashSet<>());

        return post;
    }
}


