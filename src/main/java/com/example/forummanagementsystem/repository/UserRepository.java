package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface UserRepository {

    List<User> get();

    User get(int id);
    User getByUsername(String username);
    User create(User user);

    User block(User user);

    User makeAdmin(User user);
}
