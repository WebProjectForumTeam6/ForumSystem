package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Post;

import java.util.List;

public interface PostService {
    Post get(int id);

    List<Post> get(String username, String title, Integer like, String tags);

    void create(Post post);
}
