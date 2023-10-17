package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface UserService {

   List<User> get();
   User get(int id);
   User get(String username);
   User create(User user);

   User block(User user);
}
