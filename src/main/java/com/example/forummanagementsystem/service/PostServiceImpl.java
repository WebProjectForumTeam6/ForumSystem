package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Override
    public Post get(int id) {
        return null;
    }

    @Override
    public List<Post> get(String username, String title, Integer like, String tags) {
        return null;
    }

    @Override
    public void create(Post post) {

    }
}
