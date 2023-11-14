package com.example.forummanagementsystem.service.contracts;

import com.example.forummanagementsystem.models.AdminInfo;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.UserFilterOptions;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;

import java.util.List;

public interface UserService {
    List<User> getAll();

    List<User> get(UserFilterOptions userFilterOptions);

    User getById(int id);

    User getByUsername(String username);

    User create(User user);

    User makeAdmin(User user, User userToMakeAdmin);

    void block(User user, User userToBlock);

    void unblock(User user, User userToBlock);

    User getByEmail(String email);

    User getByFirstName(String firstName);

    User updateUser(User user, User updatedUser, UserDtoUpdate userDtoUpdate);

    void deleteUser(int id, User user);

    User addProfilePhoto(User user, String url);
}
