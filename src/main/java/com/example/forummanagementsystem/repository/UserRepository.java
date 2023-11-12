package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.AdminInfo;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.UserFilterOptions;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    List<User> get(UserFilterOptions userFilterOptions);
    User getByUsername(String username);
    AdminInfo getAdminInfo(User user);
    User create(User user);
    User block(User userToBlock);
    User unblock(User userToUnblock);
    User makeAdmin(User user);
    User getByEmail(String email);
    User getByFirstName(String firstName);
    void updateUser(User userToUpdate);
    void addPhoneNumber(AdminInfo adminInfo);
    void updatePhoneNumber(AdminInfo adminInfo);
    User getById(int id);
    void deleteUser(User user);
}
