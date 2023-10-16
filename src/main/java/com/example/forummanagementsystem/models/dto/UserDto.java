package com.example.forummanagementsystem.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.checkerframework.common.aliasing.qual.Unique;

public class UserDto {
    @NotNull(message = "name can't be empty")
    @Size(min = 4, max = 32, message = "First name should be between 4 and 34 symbols.")
    private String firstName;
    @NotNull(message = "name can't be empty")
    @Size(min = 4, max = 32, message = "Last name should be between 4 and 34 symbols.")
    private String lastName;
    @Email
    @Unique
    private String email;
    @NotNull(message = "Username can't be empty.")
    private String username;
    @NotNull(message = "Password can't be empty.")
    private String password;

    public UserDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
