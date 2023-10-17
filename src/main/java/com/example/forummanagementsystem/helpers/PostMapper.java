package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.models.dto.UserDto;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    private final PostService postService;
    @Autowired
    public PostMapper(PostService postService) {
        this.postService = postService;
    }

//    public Post fromDto(int id, PostDto postDto){
//        Post post=fromDto(postDto);
//        post.setId(id);
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        return post;
//    }

//    public Post fromDto(PostDto postDto) {
//        Post post=new Post();
//        post.setComments(postDto.getComments());
//        post.setLikes(postDto.getLikes());
//        return post;
//    }
}


