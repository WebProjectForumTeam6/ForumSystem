package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface UserRepository {

    List<User> get();

    User get(int id);
    User get(String username);
    User create(User user);
}
