package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;

import java.util.List;

public interface PostRepository {

    Post getById(int id);
    Post getByTitle(String title);
    void create(Post post);
    void delete(int id);
    Post update(Post post);
    String generateOrderBy(FilterOptions filterOptions);
    List<Post> getAll(FilterOptions filterOptions);
    void modifyLike(Post post);
}


