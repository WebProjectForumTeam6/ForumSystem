package com.example.forummanagementsystem.service.contracts;

import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;

import java.util.List;

public interface PostService {
    List<Post> getAll(FilterOptions filterOptions);

    Post getById(int id);

    void create(Post post, User creator);

    void delete(int id, User user);

    Post update(PostDto postDto, User user, int postId);

    void modifyLike(int id, User user);
    List<Post> getTop10MostCommentedPosts();

    List<Post> get10MostRecentlyCreatedPosts();
}


