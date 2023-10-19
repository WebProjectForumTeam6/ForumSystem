package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.AdminInfo;
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
    public void block(User user, User userToBlock) {
        checkModifyPermissions(user);
        userToBlock.setBlocked(true);
        repository.block(userToBlock);
    }
    @Override
    public void unblock(User user, User userToUnblock){
        checkModifyPermissions(user);
        userToUnblock.setBlocked(false);
        repository.unblock(userToUnblock);
    }

    public User makeAdmin(User user, User userToMakeAdmin) {
        checkModifyPermissions(user);
        userToMakeAdmin.setAdmin(true);
        repository.makeAdmin(userToMakeAdmin);
        return user;
    }



    @Override
    public User getByEmail(String email) {
        User user = repository.getByEmail(email);

        if (user == null) {
            throw new EntityNotFoundException("User", "email", email);
        }

        return user;
    }

    @Override
    public User getByFirstName(String firstName) {
        User user = repository.getByFirstName(firstName);

        if (user == null) {
            throw new EntityNotFoundException("User", "firstName", firstName);
        }

        return user;
    }
    @Override
    public void updateUser(User user, User updatedUser){
        if (user.getId()!=updatedUser.getId()){
            throw new AuthorizationException("You can't update other users information");
        }
        repository.updateUser(updatedUser);
    }
    private void checkModifyPermissions(User user) {
        User user1 = repository.get(user.getId());
        if (!(user.isAdmin())) {
            throw new AuthorizationException(MODIFY_USER_ERROR_MESSAGE);
        }
    }

    @Override
    public AdminInfo addPhoneNumberToAdmin(User user, String phoneNumber) {
        if (!user.isAdmin()) {
            throw new AuthorizationException("Only admins can add a phone number.");
        }
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setUser(user);
        adminInfo.setPhoneNumber(phoneNumber);
        repository.updatePhoneNumber(adminInfo);
        return adminInfo;
    }


}
