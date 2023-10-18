package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface PostService {
    Post get(int id);

    List<Post> get(String username, String title, Integer like, String tags);

    void create(Post post);
    void delete(int id, User user);

    void update(Post post,User user);

}


