package com.example.forummanagementsystem;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;

import java.time.LocalDateTime;

public class Helpers {

    public static User createMockAdmin() {
        User mockUser = createMockUser();
        mockUser.setAdmin(true);
        return mockUser;
    }

    public static User createMockUser() {
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("MockUsername");
        mockUser.setPassword("MockPassword");
        mockUser.setLastName("MockLastName");
        mockUser.setFirstName("MockFirstName");
        mockUser.setEmail("mock@user.com");
        return mockUser;
    }

    public static Comment createCommentByUser() {
        Comment mockComment = new Comment();
        mockComment.setCreatedAt(LocalDateTime.now());
        mockComment.setId(1);
        mockComment.setContent("mockContent");
        mockComment.setPost(new Post());
        mockComment.setUser(createMockUser());
        return mockComment;

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
        post.setTitle("Title");
        post.setContent("Content");
        post.setCreatedBy(user);
        return post;
    }
    public static PostDto createMockDtoForUpdate(){
        PostDto postDto=new PostDto();
        postDto.setContent("Content should be between 32 and 8193 symbols.");
        postDto.setTitle("Title should be between 16 and 64 symbols.");
        return postDto;
    }
}
