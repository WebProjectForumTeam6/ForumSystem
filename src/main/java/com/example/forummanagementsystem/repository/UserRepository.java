package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface UserRepository {

    List<User> get();

    User get(int id);
    User getByUsername(String username);
    User create(User user);

    User block(User userToBlock);
    User unblock(User userToUnblock);

    User makeAdmin(User user);
    User getByEmail(String email);

    User getByFirstName(String firstName);
    void updateUser(User userToUpdate);

    void updatePhoneNumber(int userId, String phoneNumber);
}
