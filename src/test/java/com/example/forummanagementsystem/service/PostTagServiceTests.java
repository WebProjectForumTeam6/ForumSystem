package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.TagDto;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.PostTagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.forummanagementsystem.Helpers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostTagServiceTests {

    @InjectMocks
    private PostTagServiceImpl postTagService;

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostTagRepository postTagRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    void get_Should_CallRepository() {
        // Arrange
        Mockito.when(postTagRepository.getAllTags())
                .thenReturn(null);

        // Act
        postTagService.getAllTags();

        // Assert
        Mockito.verify(postTagRepository, Mockito.times(1))
                .getAllTags();
    }

    @Test
    public void get_Should_ReturnTag_When_MatchByIdExist() {
        // Arrange
        Tag mockTag = createMockTag();
        Mockito.when(postTagRepository.getTagById(Mockito.anyInt()))
                .thenReturn(mockTag);

        // Act
        Tag result = postTagService.getTagById(mockTag.getId());

        // Assert
        Assertions.assertEquals(mockTag, result);
    }

//    @Test
//    public void addTagToPost_Should_AddTags_When_TagsExist(){
//        String tags = "tag1, tag2, tag3";
//        User user = createMockUser();
//        Post post = createMockPost();
//
//    // Configure the PostTagRepository mock to return the corresponding tags
//    when(postTagRepository.getTagByContent("tag1")).thenReturn(new Tag("tag1"));
//    when(postTagRepository.getTagByContent("tag2")).thenReturn(new Tag("tag2"));
//    when(postTagRepository.getTagByContent("tag3")).thenReturn(new Tag("tag3"));
//
//    // Act
//    Post result = postTagService.addTagToPost(tags, user, post);
//
//
//
//    // Assert: Verify that the tags are added to the post
//    verify(postTagRepository, times(3)).modifyPostTags(post);
//}





//    @Test
//    public void deleteTagFromPost_Should_Remove_Tags_ifExist(){
//    String tags = "tag1, tag2, tag3";
//        User user = createMockUser();
//        Post post = createPostWithTags(tags);
//
//        Mockito.when(postTagRepository.getTagByContent("tag1")).thenReturn(new Tag("tag1"));
//
//        Post result = postTagService.deleteTagFromPost(tags,user,post);
//
//        Mockito.verify(postTagRepository,Mockito.times(1)).modifyPostTags(result);
//    }















}