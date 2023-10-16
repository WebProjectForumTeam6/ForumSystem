package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
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
    @Override
    public User create(User user){
    boolean duplicateExists=true;
    try {
        repository.get(user.getUsername());
    } catch (EntityNotFoundException e) {
        duplicateExists=false;
    }

    if (duplicateExists) {
        throw new EntityDuplicateException("User", "username", user.getUsername());
    }

    return repository.create(user);
    }

}
