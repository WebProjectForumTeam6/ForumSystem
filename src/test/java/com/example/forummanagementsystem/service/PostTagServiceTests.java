package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.Helpers;
import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
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
import java.util.HashSet;

import static com.example.forummanagementsystem.Helpers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostTagServiceTests {
    @Mock
    PostRepository mockRepository;
    @InjectMocks
    private PostTagServiceImpl postTagService;

    @Mock
    private PostTagRepository postTagRepository;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postTagService = new PostTagServiceImpl(postTagRepository, postRepository);
    }
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
        Tag mockTag=createMockTag();
        Mockito.when(postTagRepository.getTagById(Mockito.anyInt()))
                .thenReturn(mockTag);

        // Act
        Tag result = postTagService.getTagById(mockTag.getId());

        // Assert
        Assertions.assertEquals(mockTag, result);
    }
    @Test
    public void createTag_Should_CreateTagForAdminUser() {
        // Arrange
        PostTag tag = createMockPostTag();
        User adminUser = createMockAdmin();
        // Act
        postTagService.create(tag, adminUser);

        // Assert
        verify(postTagRepository, times(1)).create(tag);
    }

    @Test
    public void deleteAllTagsForPost_Should_DeleteAllTagsForPost() {
        // Arrange
        int postId = 1;
        Mockito.doNothing().when(postTagRepository).deleteAllTagsForPost(postId);

        // Act
        postTagService.deleteAllTagsForPost(postId);

        // Assert
        verify(postTagRepository, times(1)).deleteAllTagsForPost(postId);
    }

    @Test
    public void addTagToPost_Should_AddTagToPost() {
        // Arrange
        int postId = 1;
        int tagId = 2;
        Post post = createMockPost();
        post.setTags(new HashSet<>());

        when(postRepository.getById(postId)).thenReturn(post);

        // Act
        postTagService.addTagToPost(postId, tagId);
    }

    @Test
    public void removeTagFromPost_Should_NotRemoveTag_WhenTagDoesNotExist() {
        // Arrange
        int postId = 1;
        int tagId = 2;
        Post post = createMockPost();
        post.setTags(new HashSet<>());
        when(postRepository.getById(postId)).thenReturn(post);

        // Act
        postTagService.removeTagFromPost(postId, tagId);

        // Assert
        verify(postTagRepository, never()).delete(postId, tagId);
        //    Mockito.verify(postTagRepository,Mockito.times(1)).delete(postId,tagId);
    }


    @Test
    public void updateTag_FromUser_Should_Have_Permission(){
        int tagId = 1;
        String newContent ="updatedtag";
        User user = createMockUser();
        user.setBlocked(false);
        user.setAdmin(true);
        Tag tag = createMockTag();

        when(postTagRepository.getTagById(tagId)).thenReturn(tag);

        postTagService.updateTag(tagId,"updatedtag",user);

        Assertions.assertEquals(newContent,tag.getContent());
//    Assertions.assertThrows(AuthorizationException.class,
//            () -> postTagService.updateTag(tagId,newContent,user));
    }
//    @Test
//    public void updateTag_When_Have_Authorization_Exception(){
//       Tag tag = createMockTag();
//        String newContent = "Updated Content";
//        User user = createMockUser();
//        user.setAdmin(true);
//        user.setBlocked(true);
//
//        when(postTagRepository.update(tag)).thenReturn(tag);
//        Assertions.assertThrows(AuthorizationException.class, ()->
//                postTagService.updateTag(tag.getId(),newContent,user));
//    }

}