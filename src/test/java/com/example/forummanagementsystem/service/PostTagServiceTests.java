package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.Helpers;
import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.TagDto;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.PostTagRepository;
import com.example.forummanagementsystem.repository.PostTagRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.forummanagementsystem.Helpers.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostTagServiceTests {

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
        Tag mockTag = createMockTag();
        Mockito.when(postTagRepository.getTagById(Mockito.anyInt()))
                .thenReturn(mockTag);

        // Act
        Tag result = postTagService.getTagById(mockTag.getId());

        // Assert
        assertEquals(mockTag, result);
    }

    @Test
    public void createTag_Should_CreateTag_When_Tag_DoesNotExist() {
        TagDto tagDto = createTagDto("newTag");

        when(postTagRepository.getTagByContent(tagDto.getContent())).thenThrow(EntityNotFoundException.class);
        Tag result = postTagService.create(tagDto);

        verify(postTagRepository, times(1)).create(any(Tag.class));
    }

    @Test
    public void createTag_Should_ThrowEntityDuplicateException_When_TagExists() {
        TagDto tagDto = createTagDto("Tag");

        when(postTagRepository.getTagByContent(tagDto.getContent())).thenReturn(new Tag(tagDto.getContent()));

        assertThrows(EntityDuplicateException.class, () -> postTagService.create(tagDto));
    }

    @Test
    public void testDeleteAllTagsFromPost() {
        User user = Helpers.createMockUser();
        Post post = Helpers.createMockPost();
        post.addTag(Helpers.createMockTag());
        post.addTag(Helpers.createMockTag());

        when(postRepository.update(post)).thenReturn(post);

        Post resultPost = postTagService.deleteAllTagsFromPost(user, post);

        post.getTags().clear();
        verify(postRepository).update(post);
    }



    @Test
    void delete_ShouldDeleteTag() {
        // Arrange
        int tagId = 1;
        Tag tagToDelete = new Tag();
        when(postTagRepository.getTagById(tagId)).thenReturn(tagToDelete);

        // Act
        postTagService.delete(tagId);

        // Assert
        verify(postTagRepository, times(1)).getTagById(tagId);
        verify(postTagRepository, times(1)).delete(tagToDelete);
    }

    @Test
    void updateTag_ShouldUpdateTagContent() {
        // Arrange
        int tagId = 1;
        Tag existingTag = new Tag();
        existingTag.setId(tagId);
        existingTag.setContent("ExistingContent");

        TagDto tagDto = new TagDto();
        tagDto.setContent("NewContent");

        // Configure the PostTagRepository mock to return the existing tag
        when(postTagRepository.getTagById(tagId)).thenReturn(existingTag);

        // Act
        Tag result = postTagService.updateTag(tagId, tagDto);

        // Assert
        verify(postTagRepository, times(1)).update(existingTag);
    }


    @Test
    public void addTagToPost_Should_AddTags_When_TagsExist() {
        String tags = "tag1";
        User user = createMockUser();
        Post post = createMockPost();

        // Configure the PostTagRepository mock to return the corresponding tags
        when(postTagRepository.getTagByContent("tag1")).thenReturn(new Tag("tag1"));
        // Act
        Post result = postTagService.addTagToPost(tags, user, post);

        // Assert: Verify that the tags are added to the post
        verify(postTagRepository, times(1)).modifyPostTags(post);
    }

    @Test
    public void addTagToPost_When_Tag_DoesNotExist() {
        String tags = "tag1";
        User user = createMockUser();
        Post post = createMockPost();
        TagDto tagDto = new TagDto();
        tagDto.setContent(tags);


        when(postTagRepository.getTagByContent("tag1")).thenThrow(EntityNotFoundException.class);

        postTagService.addTagToPost(tags, user, post);
    }


    @Test
    public void deleteTagFromPost_Should_Remove_Tags_ifExist() {
        String tags = "tag1";
        User user = Helpers.createMockUser();
        Post post = Helpers.createMockPost();
        Tag tagToRemove = new Tag();
        tagToRemove.setContent("tag1");

        Mockito.when(postTagRepository.getTagByContent("tag1")).thenReturn(tagToRemove);

        Post result = postTagService.deleteTagFromPost(tags, user, post);

        verify(postTagRepository).getTagByContent("tag1");

        verify(postTagRepository).modifyPostTags(post);
    }


}