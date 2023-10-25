package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.PostTagRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

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
        Tag mockTag=new Tag();
        mockTag.setContent("content");
        Mockito.when(postTagRepository.getTagById(Mockito.anyInt()))
                .thenReturn(mockTag);

        // Act
        Tag result = postTagService.getTagById(mockTag.getId());

        // Assert
        Assertions.assertEquals(mockTag, result);
    }
//    @Test
//    void testCreateTagAsNonAdmin() {
//        User regularUser = new User();
//        regularUser.setAdmin(false);
//
//        PostTag postTag = new PostTag();
//
//        assertThrows(AuthorizationException.class, () -> postTagService.create(postTag, regularUser));
//    }


}

