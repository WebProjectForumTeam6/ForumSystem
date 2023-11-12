package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.UserDto;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDto(UserDto userDto) {
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDtoUpdate toDto(User user){
        UserDtoUpdate userDtoUpdate=new UserDtoUpdate();
        userDtoUpdate.setFirstName(user.getFirstName());
        userDtoUpdate.setLastName(user.getLastName());
        userDtoUpdate.setEmail(user.getEmail());
        userDtoUpdate.setPassword(user.getPassword());
        userDtoUpdate.setPasswordConfirm(user.getPassword());
        return userDtoUpdate;
    }
}
