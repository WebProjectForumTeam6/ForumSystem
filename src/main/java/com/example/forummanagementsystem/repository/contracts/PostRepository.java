package com.example.forummanagementsystem.repository.contracts;

import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getAll(FilterOptions filterOptions);

    Post getById(int id);
    Post getByTitle(String title);
    void create(Post post);
    void delete(int id);
    Post update(Post post);
    String generateOrderBy(FilterOptions filterOptions);
    void modifyLike(Post post);
    List<Post> getTop10MostCommentedPosts();
    List<Post> get10MostRecentlyCreatedPosts();
}


