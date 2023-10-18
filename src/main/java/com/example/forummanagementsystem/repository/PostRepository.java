package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.Post;

public interface PostRepository {

    Post getById(int id);

    Post get(String title);
    void create(Post post);

    void delete(int id);

    void update(Post post);
}


