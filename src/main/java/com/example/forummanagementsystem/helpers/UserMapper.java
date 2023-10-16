package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.UserDto;
import com.example.forummanagementsystem.service.UserService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserService userService;
    @Autowired
    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public User fromDto(int id, UserDto userDto){
        User user=fromDto(userDto);
        user.setId(id);
        user.setBlocked(false);
        user.setAdmin(false);
        return user;
    }

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
