package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.UserDto;
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
}
