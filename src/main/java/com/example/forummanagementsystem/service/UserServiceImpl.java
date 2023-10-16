package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final PostRepository postRepository;

@Autowired
    public UserServiceImpl(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }
    @Override
    public List<User> get(){
    return repository.get();
    }
    @Override
    public User get(int id){
    return repository.get(id);
    }

    public User get(String username){
    return  repository.get(username);
    }

}
