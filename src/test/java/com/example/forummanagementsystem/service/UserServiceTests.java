package com.example.forummanagementsystem.service;


import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;
import com.example.forummanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        when(userRepository.get(userId)).thenReturn(user);

        User result = userService.get(userId);

        assertEquals(userId, result.getId());
    }

    @Test
    public void testGetUserByUsername() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.getByUsername(username)).thenReturn(user);

        User result = userService.getByUsername(username);

        assertEquals(username, result.getUsername());
    }

    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setUsername("newuser");

        when(userRepository.getByUsername(newUser.getUsername())).thenThrow(new EntityNotFoundException("User", "Username", newUser.getUsername()));
        when(userRepository.create(newUser)).thenReturn(newUser);

        User createdUser = userService.create(newUser);

        assertEquals(newUser.getUsername(), createdUser.getUsername());
    }

    @Test
    public void testCreateUserDuplicate() {
        User existingUser = new User();
        existingUser.setUsername("existinguser");

        when(userRepository.getByUsername(existingUser.getUsername())).thenReturn(existingUser);

        assertThrows(EntityDuplicateException.class, () -> userService.create(existingUser));
    }

    @Test
    public void testBlockUser() {
        User adminUser = new User();
        User userToBlock = new User();
        userToBlock.setId(2);

        when(userRepository.get(2)).thenReturn(userToBlock);
        userService.block(adminUser, userToBlock);

        assertTrue(userToBlock.isBlocked());
    }

    @Test
    public void testUnblockUser() {
        User adminUser = new User();
        User userToUnblock = new User();
        userToUnblock.setId(2);

        when(userRepository.get(2)).thenReturn(userToUnblock);
        userService.unblock(adminUser, userToUnblock);

        assertFalse(userToUnblock.isBlocked()); // Fixed assertion
    }

    @Test
    public void testMakeAdmin() {
        User adminUser = new User();
        User userToMakeAdmin = new User();
        userToMakeAdmin.setId(3);

        userService.makeAdmin(adminUser, userToMakeAdmin);

        assertTrue(userToMakeAdmin.isAdmin());
    }

    @Test
    public void testGetUserByEmail() {
        String email = "user@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.getByEmail(email)).thenReturn(user);

        User result = userService.getByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User adminUser = new User();
        User userToUpdate = new User();
        UserDtoUpdate userDtoUpdate = new UserDtoUpdate();
        userDtoUpdate.setFirstName("UpdatedFirstName");
        userToUpdate.setId(4);

        when(userRepository.getAdminInfo(userToUpdate)).thenReturn(null);

        User updatedUser = userService.updateUser(adminUser, userToUpdate, userDtoUpdate);

        assertEquals("UpdatedFirstName", updatedUser.getFirstName());
    }

    @Test
    public void testDeleteUser() {
        int userIdToDelete = 5;
        User adminUser = new User();
        User userToDelete = new User();
        userToDelete.setId(userIdToDelete);

        when(userRepository.getById(userIdToDelete)).thenThrow(new EntityNotFoundException("User", "id", userIdToDelete));

        assertDoesNotThrow(() -> {
            userService.deleteUser(userIdToDelete, adminUser);
            Mockito.verify(userRepository, Mockito.times(1)).deleteUser(userToDelete);
            assertEquals("DeletedUser", userToDelete.getFirstName());
        });
    }

    @Test
    public void testDeleteUserUnauthorized() {
        int userIdToDelete = 5;
        User user = new User();

        Throwable exception = assertThrows(AuthorizationException.class, () -> userService.deleteUser(userIdToDelete, user));

        assertEquals("Only if you are an admin can change other user information.", exception.getMessage());
    }

    @Test
    public void testUpdateUserUnauthorized() {
        User user = new User();
        User userToUpdate = new User();
        UserDtoUpdate userDtoUpdate = new UserDtoUpdate();
        userToUpdate.setId(4);

        Throwable exception = assertThrows(AuthorizationException.class, () -> userService.updateUser(user, userToUpdate, userDtoUpdate));

        assertEquals("Only if you are an admin can change other user information.", exception.getMessage());
    }
}
