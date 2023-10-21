package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface PostService {
    Post getById(int id);

    List<Post> get();

    void create(Post post, User creator);
    void delete(int id, User user);

    void update(Post post,User user);

    List<Post> getAll(FilterOptions filterOptions);

  //  void addLikeToPost(int postId, User user);


    //    @Override
    //    public void addLikeToPost(int postId, User user) {
    //        Post post = repository.getById(postId);
    //        post.getLikes().add(user);
    //       repository.update(post);
    //    }
    void modifyLike(int id, User user, boolean likeFlag);
}


