package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post getById(int id);

    Post get(String title);
    void create(Post post);

    void delete(int id);

    void update(Post post);
    List<Post>getAll(FilterOptions filterOptions);

    String generateOrderBy(FilterOptions filterOptions);

    List<Post> getAll(FilterOptions filterOptions, String orderBy);
}


