package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface UserService {

   List<User> get();
   User get(int id);
   User getByUsername(String username);
   User create(User user);
   User makeAdmin(User user, User userToMakeAdmin);

   void block(User user, User userToBlock);
   void unblock(User user, User userToBlock);
   User getByEmail(String email);
   User getByFirstName(String firstName);
   void updateUser(User user, User updatedUser);
}
