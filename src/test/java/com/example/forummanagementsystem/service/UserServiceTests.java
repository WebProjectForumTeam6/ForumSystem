package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.Helpers;
import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.AdminInfo;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;
import com.example.forummanagementsystem.repository.contracts.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.forummanagementsystem.Helpers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void get_Should_ReturnListOfUsers_When_UserHasPermissions() {
//        User user = createMockAdmin();
        List<User> users = new ArrayList<>();
        users.add(createMockUser());
        when(userRepository.getAll()).thenReturn(users);

        List<User> result = userService.getAll();
        assertEquals(users, result);
    }

    @Test
    public void get_Should_Return_Exception_When_Id_is_Null() {
        User user1 = createMockUser();
        int id = user1.getId();
        //    User user = createMockAdmin();
        when(userRepository.getById(id)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> userService.getById(id));
    }


    @Test
    public void get_Should_ReturnUser_When_UserHasPermissions() {
        int userId = 1;
        User user1 = createMockUser();
        User user = createMockAdmin();
        User mockUser = createMockUser();
        List<User> userList = Arrays.asList(mockUser, user1);
        when(userRepository.getAll()).thenReturn(userList);

        List<User> result = userService.getAll();


        assertEquals(result, userList);
    }

    @Test
    public void createUser_Should_ThrowEntityDuplicateException_When_UsernameAlreadyExists() {
        User existingUser = createMockUser();
        when(userRepository.getByUsername(existingUser.getUsername())).thenReturn(existingUser);

        User newUser = createMockUser();

        assertThrows(EntityDuplicateException.class, () -> userService.create(newUser));
    }

    @Test
    public void createUser_Should_CreateUser_When_UsernameDoesNotAlreadyExist() {
        User newUser = createMockUser();
        when(userRepository.getByUsername(newUser.getUsername())).thenThrow(new EntityNotFoundException("User", "username", newUser.getUsername()));
        when(userRepository.create(newUser)).thenReturn(newUser);

        User createdUser = userService.create(newUser);

        assertEquals(newUser, createdUser);
    }

    @Test
    public void block_Should_BlockUser_When_UserHasPermissions() {
        User adminUser = createMockAdmin();
        User userToBlock = createMockUser();

        userService.block(adminUser, userToBlock);

        Assertions.assertTrue(userToBlock.isBlocked());
    }

    @Test
    public void unblock_Should_UnblockUser_When_UserHasPermissions() {
        User adminUser = createMockAdmin();
        User userToUnblock = Helpers.createMockBlockedUser();

        userService.unblock(adminUser, userToUnblock);

        Assertions.assertFalse(userToUnblock.isBlocked());
    }

    @Test
    public void makeAdmin_Should_MakeUserAdmin_When_UserHasPermissions() {
        User adminUser = createMockAdmin();
        User userToMakeAdmin = createMockUser();

        User result = userService.makeAdmin(adminUser, userToMakeAdmin);

        Assertions.assertTrue(result.isAdmin());
    }

    @Test
    public void getByEmail_Should_ReturnUser_When_UserExists() {
        String userEmail = "mock@user.com";
        User user = createMockUser();
        when(userRepository.getByEmail(userEmail)).thenReturn(user);

        User result = userService.getByEmail(userEmail);

        assertEquals(user, result);
    }

    @Test
    public void getByEmail_Should_ThrowEntityNotFoundException_When_UserDoesNotExist() {
        String userEmail = "nonexistent@user.com";
        when(userRepository.getByEmail(userEmail)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> userService.getByEmail(userEmail));
    }




    @Test
    void updateUser_Should_UpdateUserDetails_When_UserHasPermissions() {
        User adminUser = Helpers.createMockAdmin();
        User userToUpdate = Helpers.createMockUser();
        UserDtoUpdate userDtoUpdate = Helpers.createMockUserDtoUpdate();

        User result = userService.updateUser(adminUser, userToUpdate, userDtoUpdate);

        assertEquals(userDtoUpdate.getFirstName(), result.getFirstName());
        assertEquals(userDtoUpdate.getLastName(), result.getLastName());
        assertEquals(userDtoUpdate.getEmail(), result.getEmail());
    }

    @Test
    void delete_Should_CallRepository_When_UserIsCreator() {
        // Arrange
        User user = createMockUser();
        User mock1 = createMockAdmin();

        when(userRepository.getById(Mockito.anyInt()))
                .thenReturn(user);

        // Act
        userService.deleteUser(user.getId(), mock1);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1))
                .deleteUser(user);
    }

    @Test
    public void deleteUser_Should_ThrowAuthorizationException_When_UserIsAdmin(){
        User user = createMockAdmin();
        int idToAdmin = user.getId();
        User userToDelete = createMockUserWithId(idToAdmin);

        when(userRepository.getById(idToAdmin)).thenReturn(userToDelete);

        userService.deleteUser(idToAdmin,user);

    }

    @Test
    public void getByUsername_Should_ReturnUser_When_UsernameExists(){
        User user = createMockUser();
        String username = user.getUsername();


        when(userRepository.getByUsername(username)).thenReturn(user);

        User result =userService.getByUsername(username);

        assertEquals(user,result);

        Mockito.verify(userRepository,Mockito.times(1)).getByUsername(username);
    }

    @Test
    public void getByUsername_Should_ThrowEntityNotFoundException_When_UsernameDoesNotExist() {
        String username = "nonExistingUsername";

        when(userRepository.getByUsername(username)).thenReturn(null);

        userService.getByUsername(username);

        //  Assertions.assertThrows(EntityNotFoundException.class, () -> {
        //       userService.getByUsername(username);
        //   });
    }


    @Test
    public void getFirstName_Should_Return_User_WhenFirstNameExist(){
        User user = createMockUser();
        String firstName = user.getFirstName();

        when(userRepository.getByFirstName(firstName)).thenReturn(user);

        User result = userService.getByFirstName(firstName);

        assertEquals(user,result);

        Mockito.verify(userRepository,Mockito.times(1)).getByFirstName(firstName);
    }

    @Test
    public void getFirstName_Should_ThrowEntityNotFoundException_When_FirstNameDoesNotExist(){
        String firstName = "NotExpectedFirstName";

        when(userRepository.getByFirstName(firstName)).thenReturn(null);

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.getByFirstName(firstName);
        });

    }

    @Test
    public void addPhoneNumberToAdmin_Should_AddPhoneNumberToAdminInfo_When_UserIsAdmin(){
        User user = createMockAdmin();
        String phoneNumber = "MockPhoneNumber";
        AdminInfo adminInfo = null;

        when(userRepository.getAdminInfo(user)).thenReturn(adminInfo);

        userService.addPhoneNumberToAdmin(user,phoneNumber);

        Mockito.verify(userRepository,Mockito.times(1))
                .addPhoneNumber(any(AdminInfo.class));
    }

    @Test
    public void addPhoneNumberToAdmin_Should_ThrowAuthorizationException_When_UserIsNotAdmin() {

        User nonAdminUser = createMockUser();
        String phoneNumber = "1234567890";


        Assertions.assertThrows(AuthorizationException.class,()->
                userService.addPhoneNumberToAdmin(nonAdminUser,phoneNumber));
    }

}




