package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.Helpers;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;
import com.example.forummanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository mockRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(mockRepository);
    }

    @Test
    public void get_Should_ReturnListOfUsers_When_UserHasPermissions() {
        List<User> users = new ArrayList<>();
        users.add(Helpers.createMockUser());
        Mockito.when(mockRepository.getAll()).thenReturn(users);

        List<User> result = userService.getAll();

        Assertions.assertEquals(users, result);
    }

    @Test
    public void get_Should_ThrowEntityNotFoundException_When_NoUsersExist() {
        Mockito.when(mockRepository.getAll()).thenReturn(new ArrayList<>());

        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.getAll());
    }

    @Test
    public void get_Should_ReturnUser_When_UserHasPermissions() {
        int userId = 1;
        User user1= Helpers.createMockUser();
        User user = Helpers.createMockAdmin();
        User mockUser = Helpers.createMockUser();
        List<User>userList = Arrays.asList(mockUser,user1);
        Mockito.when(mockRepository.getAll()).thenReturn(userList);

        List<User>result = userService.getAll();


        Assertions.assertEquals(result, userList);
    }

    @Test
    public void createUser_Should_ThrowEntityDuplicateException_When_UsernameAlreadyExists() {
        User existingUser = Helpers.createMockUser();
        Mockito.when(mockRepository.getByUsername(existingUser.getUsername())).thenReturn(existingUser);

        User newUser = Helpers.createMockUser();

        Assertions.assertThrows(EntityDuplicateException.class, () -> userService.create(newUser));
    }

    @Test
    public void createUser_Should_CreateUser_When_UsernameDoesNotAlreadyExist() {
        User newUser = Helpers.createMockUser();
        Mockito.when(mockRepository.getByUsername(newUser.getUsername())).thenThrow(new EntityNotFoundException("User", "username", newUser.getUsername()));
        Mockito.when(mockRepository.create(newUser)).thenReturn(newUser);

        User createdUser = userService.create(newUser);

        Assertions.assertEquals(newUser, createdUser);
    }

    @Test
    public void block_Should_BlockUser_When_UserHasPermissions() {
        User adminUser = Helpers.createMockAdmin();
        User userToBlock = Helpers.createMockUser();

        userService.block(adminUser, userToBlock);

        Assertions.assertTrue(userToBlock.isBlocked());
    }

    @Test
    public void unblock_Should_UnblockUser_When_UserHasPermissions() {
        User adminUser = Helpers.createMockAdmin();
        User userToUnblock = Helpers.createMockBlockedUser();

        userService.unblock(adminUser, userToUnblock);

        Assertions.assertFalse(userToUnblock.isBlocked());
    }

    @Test
    public void makeAdmin_Should_MakeUserAdmin_When_UserHasPermissions() {
        User adminUser = Helpers.createMockAdmin();
        User userToMakeAdmin = Helpers.createMockUser();

        User result = userService.makeAdmin(adminUser, userToMakeAdmin);

        Assertions.assertTrue(result.isAdmin());
    }

    @Test
    public void getByEmail_Should_ReturnUser_When_UserExists() {
        String userEmail = "mock@user.com";
        User user = Helpers.createMockUser();
        Mockito.when(mockRepository.getByEmail(userEmail)).thenReturn(user);

        User result = userService.getByEmail(userEmail);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void getByEmail_Should_ThrowEntityNotFoundException_When_UserDoesNotExist() {
        String userEmail = "nonexistent@user.com";
        Mockito.when(mockRepository.getByEmail(userEmail)).thenReturn(null);

        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.getByEmail(userEmail));
    }


    @Test
    public void updateUser_Should_UpdateUser_When_UserHasPermissions() {
        User adminUser = Helpers.createMockAdmin();
        User userToUpdate = Helpers.createMockUser();
        UserDtoUpdate userDtoUpdate = Helpers.createMockUserDtoUpdate();

        User result = userService.updateUser(adminUser, userToUpdate, userDtoUpdate);

        Assertions.assertEquals(userDtoUpdate.getFirstName(), result.getFirstName());
        Assertions.assertEquals(userDtoUpdate.getLastName(), result.getLastName());
        Assertions.assertEquals(userDtoUpdate.getEmail(), result.getEmail());
        Assertions.assertEquals(userDtoUpdate.getPassword(), result.getPassword());
    }

    @Test
    void delete_Should_CallRepository_When_UserIsCreator() {
        // Arrange
        User user =Helpers.createMockUser();
        User mock1 = Helpers.createMockAdmin();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(user);

        // Act
        userService.deleteUser(user.getId(), mock1);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .deleteUser(user);
    }

//    @Test
//    void delete_Should_CallRepository_When_UserIsAdmin() {
//        // Arrange
//        User mockUserAdmin = createMockAdmin();
//        Beer mockBeer = createMockBeer();
//
//        Mockito.when(mockRepository.get(Mockito.anyInt()))
//                .thenReturn(mockBeer);
//
//        // Act
//        service.delete(1, mockUserAdmin);
//
//        // Assert
//        Mockito.verify(mockRepository, Mockito.times(1))
//                .delete(1);
//    }
//
//    @Test
//    void delete_Should_ThrowException_When_UserIsNotAdminOrCreator() {
//        // Arrange
//        Beer mockBeer = createMockBeer();
//
//        Mockito.when(mockRepository.get(Mockito.anyInt()))
//                .thenReturn(mockBeer);
//
//        User mockUser = Mockito.mock(User.class);
//
//        // Act, Assert
//        Assertions.assertThrows(
//                AuthorizationException.class,
//                () -> service.delete(1, mockUser));
//    }

}




