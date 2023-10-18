package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.Post;

public interface PostRepository {
    Post getById(int id);
}
