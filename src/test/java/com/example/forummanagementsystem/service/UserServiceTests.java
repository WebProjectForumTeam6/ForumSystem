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
import org.mockito.MockitoAnnotations;
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
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testGetUser() {
        User testUser = new User();
        testUser.setId(1);
        when(userRepository.get(1)).thenReturn(testUser);

        User result = userService.get(1);
        assertEquals(1, result.getId());
    }


    @Test
    public void testCreateUserDuplicate() {
        User testUser = new User();
        testUser.setUsername("existinguser");

        when(userRepository.getByUsername("existinguser")).thenReturn(new User());

        try {
            userService.create(testUser);
            fail("Expected EntityDuplicateException");
        } catch (EntityDuplicateException e) {
        }
    }

    @Test
    public void testBlockUser() {
        User adminUser = new User();
        adminUser.setAdmin(true);

        User userToBlock = new User();

        userService.block(adminUser, userToBlock);
        assertTrue(userToBlock.isBlocked());
    }

    @Test
    public void testUnblockUser() {
        User adminUser = new User();
        adminUser.setAdmin(true);

        User userToUnblock = new User();
        userToUnblock.setBlocked(true);

        userService.unblock(adminUser, userToUnblock);
        assertFalse(userToUnblock.isBlocked());
    }

    @Test
    public void testMakeAdmin() {
        User adminUser = new User();
        adminUser.setAdmin(true);

        User userToMakeAdmin = new User();

        User result = userService.makeAdmin(adminUser, userToMakeAdmin);
        assertTrue(userToMakeAdmin.isAdmin());
        assertEquals(userToMakeAdmin, result);
    }

    @Test
    public void testGetByEmail() {
        User testUser = new User();
        testUser.setEmail("testuser@example.com");
        when(userRepository.getByEmail("testuser@example.com")).thenReturn(testUser);

        User result = userService.getByEmail("testuser@example.com");
        assertEquals("testuser@example.com", result.getEmail());
    }

    @Test
    public void testGetByFirstName() {
        User testUser = aUser();
        testUser.setFirstName("John");
        when(userRepository.getByFirstName("John")).thenReturn(testUser);

        User result = userService.getByFirstName("John");
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testUpdateUser() {
        User user = aUser();
        user.setId(1);

        User updatedUser = aUser();
        updatedUser.setId(1);
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setUsername("updateduser");
        updatedUser.setPassword("updatedpassword");

        UserDtoUpdate userDtoUpdate = new UserDtoUpdate();
        userDtoUpdate.setFirstName("UpdatedFirstName");
        userDtoUpdate.setLastName("UpdatedLastName");
        userDtoUpdate.setEmail("updated@example.com");
        userDtoUpdate.setPassword("updatedpassword");

        User result = userService.updateUser(user, updatedUser, userDtoUpdate);
        assertEquals("UpdatedFirstName", result.getFirstName());
        assertEquals("UpdatedLastName", result.getLastName());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("updatedpassword", result.getPassword());
    }


    @Test
    public void testAddPhoneNumberToAdminUnauthorized() {
        User regularUser = aUser();

        try {
            userService.addPhoneNumberToAdmin(regularUser, "1234567890");
            fail("Expected AuthorizationException");
        } catch (AuthorizationException e) {
        }
    }

    @Test
    public void testCheckModifyPermissionsUnauthorized() {
        User regularUser = aUser();

        try {
            userService.checkModifyPermissions(regularUser);
            fail("Expected AuthorizationException");
        } catch (AuthorizationException e) {
        }
    }

    @Test
    public void testCheckAccessPermissionUnauthorized() {
        User regularUser = aUser();
        regularUser.setId(1);

        User updatedUser = aUser();
        updatedUser.setId(2);

        try {
            userService.checkAccessPermission(regularUser, updatedUser);
            fail("Expected AuthorizationException");
        } catch (AuthorizationException e) {
        }
    }

    @Test
    public void testDeleteUser() {
        User userToDelete = aUser();
        userToDelete.setId(1);

        when(userRepository.get(1)).thenReturn(userToDelete);

        userService.deleteUser(1, userToDelete);
        assertEquals("DeletedUser", userToDelete.getFirstName());
        assertEquals("DeletedUser", userToDelete.getLastName());
        assertEquals("deletedUser@example.com", userToDelete.getEmail());
        assertEquals("DeletedUser", userToDelete.getUsername());
        assertEquals("DeletedUser", userToDelete.getPassword());
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.get(1)).thenReturn(null);

        User userToDelete = aUser();

        try {
            userService.deleteUser(1, userToDelete);
            fail("Expected EntityNotFoundException");
        } catch (EntityNotFoundException e) {
        }
    }

    @Test
    public void testDeleteUserAdmin() {
        when(userRepository.get(1)).thenReturn(null);

        User adminUser = new User();
        adminUser.setAdmin(true);

        try {
            userService.deleteUser(1, adminUser);
            fail("Expected EntityNotFoundException");
        } catch (EntityNotFoundException e) {
        }
    }

    @Test
    public void testUpdateUserById() {
        User existingUser = aUser();
        existingUser.setId(1);

        User updatedUser = aUser();
        updatedUser.setId(1);
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setUsername("updateduser");
        updatedUser.setPassword("updatedpassword");

        when(userRepository.get(1)).thenReturn(existingUser);

        User result = userService.updateUser(1, updatedUser);
        assertEquals("UpdatedFirstName", result.getFirstName());
        assertEquals("UpdatedLastName", result.getLastName());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("updateduser", result.getUsername());
        assertEquals("updatedpassword", result.getPassword());
    }

    @Test
    public void testUpdateUserByIdNotFound() {
        when(userRepository.get(1)).thenReturn(null);

        User updatedUser = aUser();
        updatedUser.setId(1);

        try {
            userService.updateUser(1, updatedUser);
            fail("Expected EntityNotFoundException");
        } catch (EntityNotFoundException e) {
        }
    }

    private User aUser() {
        User user = new User();
        user.setAdmin(false);
        return user;
    }
}
