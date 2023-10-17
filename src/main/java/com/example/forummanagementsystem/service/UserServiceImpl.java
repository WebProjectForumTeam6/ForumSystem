package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public static final String MODIFY_USER_ERROR_MESSAGE = "Only Admin can block user!";
    private final UserRepository repository;
    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @Override
    public List<User> get() {
        return repository.get();
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }

    @Override
    public User create(User user) {
        boolean duplicateExists = true;
        try {
            repository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }

        return repository.create(user);
    }

    @Override
    public User block(User user, User userToBlock) {
        checkModifyPermissions(user);
        userToBlock.setBlocked(true);
        repository.block(userToBlock);
        return user;
    }
    @Override
    public User unblock(User user, User userToUnblock){
        checkModifyPermissions(user);
        userToUnblock.setBlocked(false);
        repository.unblock(userToUnblock);
        return userToUnblock;
    }

    public User makeAdmin(User user, User userToMakeAdmin) {
        checkModifyPermissions(user);
        userToMakeAdmin.setAdmin(true);
        repository.makeAdmin(userToMakeAdmin);
        return user;
    }


    private void checkModifyPermissions(User user) {
        User user1 = repository.get(user.getId());
        if (!(user.isAdmin())) {
            throw new AuthorizationException(MODIFY_USER_ERROR_MESSAGE);
        }
    }
    @Override
    public User getByEmail(String email) {
        User user = repository.getByEmail(email);

        if (user == null) {
            throw new EntityNotFoundException("User", "email", email);
        }

        return user;
    }

}
