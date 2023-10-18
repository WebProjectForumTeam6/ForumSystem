package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.Post;

public interface PostRepository {

    Post get(int id);

    Post get(String title);

    public void delete(int id);

    void update(Post post);
}


