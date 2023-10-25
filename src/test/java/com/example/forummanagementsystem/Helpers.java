package com.example.forummanagementsystem;

import com.example.forummanagementsystem.models.*;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;

import java.time.LocalDateTime;

public class Helpers {

    public static User createMockAdmin() {
        User mockUser = createMockUser();
        mockUser.setId(2);
        mockUser.setAdmin(true);
        return mockUser;
    }

    public static User createMockUser() {
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("MockUsername");
        mockUser.setPassword("MockPassword");
        mockUser.setLastName("MockLastName");
        mockUser.setFirstName("MockFirstName");
        mockUser.setEmail("mock@user.com");
        return mockUser;
    }

    public static Comment createCommentByUser() {
        var mockComment = new Comment();
        mockComment.setCreatedAt(LocalDateTime.now());
        mockComment.setId(1);
        mockComment.setContent("mockContent");
        mockComment.setPost(new Post());
        mockComment.setUser(createMockUser());
        return mockComment;

    }
    public static User createMockBlockedUser() {
        User user = createMockUser();
        user.setBlocked(true);
        return user;
    }

    public static AdminInfo createMockAdminInfo() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setUser(createMockAdmin());
        adminInfo.setPhoneNumber("1234567890");
        return adminInfo;
    }
    public static User createMockUserWithId(int id) {
        User user = createMockUser();
        user.setId(id);
        return user;
    }
    public static UserDtoUpdate createMockUserDtoUpdate() {
        UserDtoUpdate dto = new UserDtoUpdate();
        dto.setFirstName("UpdatedFirstName");
        dto.setLastName("UpdatedLastName");
        dto.setEmail("updated@user.com");
        dto.setPassword("UpdatedPassword");
        return dto;
    }




    public static FilterOptions createMockFilterOptions() {
        return new FilterOptions(
                "mockCreatedBy",
                "mockTitle",
                "mockContent",
                "MockSortBY",
                "sort");
    }
    public static Post createMockPost(){
        User user=createMockUser();
        Post post=new Post();
        post.setId(1);
        post.setTitle("Title1234567891011");
        post.setContent("Content1234567891011");
        post.setCreatedBy(user);
        return post;
    }
    public static PostDto createMockDtoForUpdate(){
        PostDto postDto=new PostDto();
        postDto.setContent("Content12345678910");
        postDto.setTitle("Title1234567891011");
        return postDto;
    }

}
