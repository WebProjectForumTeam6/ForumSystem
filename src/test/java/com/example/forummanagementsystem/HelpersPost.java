package com.example.forummanagementsystem;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;

public class HelpersPost {
    public static User createMockUser(){
        User user=new User();
        user.setId(1);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("Username");
        user.setPassword("Password");
        user.setBlocked(false);
        return user;
    }
    public static User createMockAdmin(){
        User user=createMockUser();
        user.setAdmin(true);
        return user;
    }
    public static Post createMockPost(){
        User user=createMockUser();
        Post post=new Post();
        post.setId(1);
        post.setTitle("Title");
        post.setContent("Content");
        post.setCreatedBy(user);
        return post;
    }
    public static PostDto createMockDtoForUpdate(){
        PostDto postDto=new PostDto();
        postDto.setContent("Content");
        postDto.setTitle("Title");
    return postDto;
    }
}
