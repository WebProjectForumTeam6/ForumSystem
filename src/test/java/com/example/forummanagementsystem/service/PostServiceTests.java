package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.repository.contracts.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.example.forummanagementsystem.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {
    @Mock
    PostRepository mockRepository;

    @InjectMocks
    PostServiceImpl postService;

    @BeforeEach
    public void setUp() {
        postService = new PostServiceImpl(mockRepository);
    }

    @Test
    void getAll_Should_CallRepository() {
        // Arrange
        FilterOptions mockFilterOptions = createMockFilterOptions();
        Mockito.when(mockRepository.getAll(mockFilterOptions))
                .thenReturn(null);

        // Act
        postService.getAll(mockFilterOptions);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .getAll(mockFilterOptions);
    }

    @Test
    public void getById_Should_ReturnPost_When_MatchByIdExist() {
        //Arrange
        Post post = createMockPost();
        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        Post result = postService.getById(post.getId());

        //Assert
        Assertions.assertEquals(post, result);
    }

    @Test
    public void create_Should_CallRepository_When_Arguments_AreValid() {
        //Arrange
        Post post = createMockPost();

        User user = createMockUser();

        //Act
        postService.create(post, user);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .create(post);
    }

    @Test
    public void update_Should_CallRepository_When_UserIsCreator() {
        //Arrange
        User user = createMockUser();

        Post post = createMockPost();

        PostDto postDto = new PostDto();
        postDto.setContent("content");
        postDto.setTitle("title");

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);


        //Act
        postService.update(postDto, user, post.getId());

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .update(post);
    }

    @Test
    public void update_Should_CallRepository_When_UserIsAdmin() {
        //Arrange
        User user = createMockUser();

        Post post = createMockPost();

        PostDto postDto = new PostDto();
        postDto.setContent("content");
        postDto.setTitle("title");

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);


        //Act
        postService.update(postDto, user, post.getId());

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .update(post);
    }

    @Test
    public void update_Should_CallRepository_When_UpdatingExistingPost() {
        //Arrange
        User user = createMockUser();
        Post post = createMockPost();

        PostDto postDto = createMockDtoForUpdate();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        postService.update(postDto, user, post.getId());

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .update(post);
    }

    @Test
    public void update_Should_CallRepository_When_UserIsNotCreatorOrAdmin() {
        //Arrange
        Post post = createMockPost();
        PostDto postDto = createMockDtoForUpdate();
        User user = createMockUser();
        user.setId(2);

        Mockito.when(mockRepository.getById(post.getId()))
                .thenReturn(post);

        //Act, Assert
        Assertions.assertThrows(AuthorizationException.class, () -> postService.update(postDto, user, post.getId()));
    }

    @Test
    public void delete_Should_CallRepository_When_UserIsAdmin() {
        //Arrange
        User user = createMockAdmin();
        user.setId(2);
        Post post = createMockPost();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        postService.delete(post.getId(), user);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .delete(post.getId());
    }

    @Test
    public void delete_Should_CallRepository_When_UserIsCreator() {
        //Arrange
        User user = createMockUser();
        Post post = createMockPost();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act
        postService.delete(post.getId(), user);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .delete(post.getId());
    }

    @Test
    public void delete_Should_ThrowException_When_UserIsNotCreatorOrAdmin() {
        //Arrange
        User user = createMockUser();
        Post post = createMockPost();
        user.setId(2);

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);

        //Act, Assert
        Assertions.assertThrows(AuthorizationException.class, () -> postService.delete(post.getId(), user));
    }

    @Test
    public void modifyLike_Should_CallRepository_WhenArguments_AreValid() {
        //Arrange
        User user = createMockUser();
        Post post = createMockPost();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(post);
        //Act
        postService.modifyLike(post.getId(), user);

        //Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .modifyLike(post);
    }
    @Test
    public void getTop10CommentedPosts_Should_Return_top10CommentedPosts(){
        List<Post> expectedPosts = new ArrayList<>();

        Mockito.when(mockRepository.getTop10MostCommentedPosts()).thenReturn(expectedPosts);

       List<Post> result=  postService.getTop10MostCommentedPosts();

        Assertions.assertEquals(expectedPosts,result);
    }

    @Test
    public void getTop10RecentlyPosts_Should_Return_Top10RecentlyPosts(){
        List<Post> recentlyPosts = new ArrayList<>();

        Mockito.when(mockRepository.get10MostRecentlyCreatedPosts()).thenReturn(recentlyPosts);
        List<Post> result = postService.get10MostRecentlyCreatedPosts();

        Assertions.assertEquals(recentlyPosts,result);
    }




}
