package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.AdminInfo;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;

import java.util.List;

public interface UserService {

   List<User> get(User user);
   User get(int id);
   User getByUsername(String username);
   User create(User user);
   User makeAdmin(User user, User userToMakeAdmin);

   void block(User user, User userToBlock);
   void unblock(User user, User userToBlock);
   User getByEmail(String email);
   User getByFirstName(String firstName);
   User updateUser(User user, User updatedUser, UserDtoUpdate userDtoUpdate);
   void deleteUser(int id, User user);
  
}
