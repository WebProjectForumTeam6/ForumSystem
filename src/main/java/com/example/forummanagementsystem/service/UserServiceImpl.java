package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.AdminInfo;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.UserFilterOptions;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;
import com.example.forummanagementsystem.repository.contracts.UserRepository;
import com.example.forummanagementsystem.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.forummanagementsystem.constants.Constants.User.MODIFY_USER_ERROR_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

    public static final String PHONE_NUMBER_ERROR = "Only admins can add a phone number.";
    public static final String NOT_AN_ADMIN_ERROR = "Only if you are an admin can change other user information.";
    public static final String ADMINS_ERROR = "Admins can not be deleted.";
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
    @Override
    public List<User> get(UserFilterOptions userFilterOptions) {
        return userRepository.get(userFilterOptions);
    }

    @Override
    public User getById(int id) {
        if (userRepository.getById(id)==null){
            throw new EntityNotFoundException("User", "id", id);
        }
        return userRepository.getById(id);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User create(User user) {
        boolean duplicateExists = true;
        try {
            userRepository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }

        return userRepository.create(user);
    }

    @Override
    public void block(User user, User userToBlock) {
        checkModifyPermissions(user);
        userToBlock.setBlocked(true);
        userRepository.block(userToBlock);
    }

    @Override
    public void unblock(User user, User userToUnblock) {
        checkModifyPermissions(user);
        userToUnblock.setBlocked(false);
        userRepository.unblock(userToUnblock);
    }

    public User makeAdmin(User user, User userToMakeAdmin) {
        checkModifyPermissions(user);
        userToMakeAdmin.setAdmin(true);
        userRepository.makeAdmin(userToMakeAdmin);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userRepository.getByEmail(email);

        if (user == null) {
            throw new EntityNotFoundException("User", "email", email);
        }

        return user;
    }

    @Override
    public User getByFirstName(String firstName) {
        User user = userRepository.getByFirstName(firstName);

        if (user == null) {
            throw new EntityNotFoundException("User", "firstName", firstName);
        }

        return user;
    }

    @Override
    public User updateUser(User user, User updatedUser, UserDtoUpdate userDtoUpdate) {
        checkAccessPermission(user, updatedUser);
        if (userDtoUpdate.getFirstName() != null) {
            updatedUser.setFirstName(userDtoUpdate.getFirstName());
        }
        if (userDtoUpdate.getLastName() != null) {
            updatedUser.setLastName(userDtoUpdate.getLastName());
        }
        if (userDtoUpdate.getEmail() != null) {
            updatedUser.setEmail(userDtoUpdate.getEmail());
        }
        if (userDtoUpdate.getPassword() != null &&
                userDtoUpdate.getPassword().equals(userDtoUpdate.getPasswordConfirm())) {
            updatedUser.setPassword(userDtoUpdate.getPassword());
        }
        if ((userDtoUpdate.getPhoneNumber() != null &&
                user.getId() == updatedUser.getId())) {
            addPhoneNumberToAdmin(updatedUser, userDtoUpdate.getPhoneNumber());
        }
        userRepository.updateUser(updatedUser);
        return updatedUser;
    }
    @Override
    public void deleteUser(int id, User user) {
        checkUserAuthorization(id, user);
        User userToDelete= getById(id);
        if (userToDelete.isAdmin()){
            throw new AuthorizationException(ADMINS_ERROR);
        }
        userToDelete.setFirstName("DeletedUser");
        userToDelete.setLastName("DeletedUser");
        userToDelete.setEmail("deletedUser@example.com");
        userToDelete.setUsername("DeletedUser");
        userToDelete.setPassword("DeletedUser");
        userToDelete.setBlocked(true);
        boolean userExists = true;
        userRepository.deleteUser(userToDelete);

    }

    public void addPhoneNumberToAdmin(User user, String phoneNumber) {
        if (!user.isAdmin()) {
            throw new AuthorizationException(PHONE_NUMBER_ERROR);
        }

        if (userRepository.getAdminInfo(user) == null) {
            AdminInfo adminInfo = new AdminInfo();
            adminInfo.setUser(user);
            adminInfo.setPhoneNumber(phoneNumber);
            userRepository.addPhoneNumber(adminInfo);
        } else {
            AdminInfo adminInfo = userRepository.getAdminInfo(user);
            adminInfo.setPhoneNumber(phoneNumber);
            userRepository.updatePhoneNumber(adminInfo);
        }
    }

    private void checkModifyPermissions(User user) {
        if (!user.isAdmin()) {
            throw new AuthorizationException(MODIFY_USER_ERROR_MESSAGE);
        }
    }

    private void checkAccessPermission(User user, User updated) {
        if (!(user.isAdmin() || user.getId() == updated.getId())) {
            throw new AuthorizationException(NOT_AN_ADMIN_ERROR);
        }
    }



    public static void checkUserAuthorization(int targetUserId, User executingUser) {
        if (!executingUser.isAdmin() && executingUser.getId() != targetUserId) {
            throw new AuthorizationException(NOT_AN_ADMIN_ERROR);
        }
    }

    @Override
    public User addProfilePhoto(User user, String url) {
        user.setProfilePhoto(url);
        return userRepository.addProfilePhoto(user);
    }
}
