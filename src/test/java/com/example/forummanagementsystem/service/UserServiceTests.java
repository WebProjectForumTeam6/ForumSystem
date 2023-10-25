package com.example.forummanagementsystem.service;


import com.example.forummanagementsystem.Helpers;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

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
        Mockito.when(userRepository.get(1)).thenReturn(testUser);

        User result = userService.get(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testCreateUserDuplicate() {
        User testUser = new User();
        testUser.setUsername("existinguser");

        Mockito.when(userRepository.getByUsername("existinguser")).thenReturn(new User());

        assertThrows(EntityDuplicateException.class, () -> userService.create(testUser));
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
        Mockito.when(userRepository.getByEmail("testuser@example.com")).thenReturn(testUser);

        User result = userService.getByEmail("testuser@example.com");
        assertEquals("testuser@example.com", result.getEmail());
    }

    @Test
    public void testGetByFirstName() {
        User testUser = new User();
        testUser.setFirstName("John");
        Mockito.when(userRepository.getByFirstName("John")).thenReturn(testUser);

        User result = userService.getByFirstName("John");
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1);

        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");
        updatedUser.setEmail("updated@example.com");
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

//    @Test
//    public void testAddPhoneNumberToAdminUnauthorized() {
//        User regularUser = new User();
//        assertThrows(AuthorizationException.class, () -> userService.addPhoneNumberToAdmin(regularUser, "1234567890"));
//    }
//
//    @Test
//    public void testCheckModifyPermissionsUnauthorized() {
//        User regularUser = new User();
//        assertThrows(AuthorizationException.class, () -> userService.checkModifyPermissions(regularUser));
//    }
//
//    @Test
//    public void testCheckAccessPermissionUnauthorized() {
//        User regularUser = new User();
//        regularUser.setId(1);
//        User updatedUser = new User();
//        updatedUser.setId(2);
//
//        assertThrows(AuthorizationException.class, () -> userService.checkAccessPermission(regularUser, updatedUser));
//    }

    @Test
    public void testDeleteUser() {
        User userToDelete = new User();
        userToDelete.setId(1);
        Mockito.when(userRepository.get(1)).thenReturn(userToDelete);

        userService.deleteUser(1, userToDelete);
        assertEquals("DeletedUser", userToDelete.getFirstName());
        assertEquals("DeletedUser", userToDelete.getLastName());
        assertEquals("deletedUser@example.com", userToDelete.getEmail());
        assertEquals("DeletedUser", userToDelete.getUsername());
        assertEquals("DeletedUser", userToDelete.getPassword());
    }

    @Test
    public void testDeleteUserNotFound() {
        User user=Helpers.createMockUser();
        user.setAdmin(true);
//        Mockito.when(userRepository.get(user.getId())).thenReturn(user);

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(5, user));
    }

    @Test
    public void testDeleteUserAdmin() {
        User adminUserToDelete = new User();
        adminUserToDelete.setAdmin(true);
        adminUserToDelete.setId(1);

        User adminUser = new User();
        adminUser.setAdmin(true);
        adminUser.setId(2);
//        Mockito.when(userRepository.get(1)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(1, adminUser));
    }

}
