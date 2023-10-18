package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.repository.PostRepository;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> get(String username, String title, Integer like, String tags) {
        return null;
    }
    public Post getById(int id){
        return postRepository.getById(id);
    }
    @Override
    public void create(Post post) {

    }
}
