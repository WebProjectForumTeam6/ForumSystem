package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    private final PostService postService;
    @Autowired
    public PostMapper(PostService postService) {
        this.postService = postService;
    }

    public Post fromDtoIn(PostDto postDto, User creator) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedBy(creator);
        return post;
    }
}


