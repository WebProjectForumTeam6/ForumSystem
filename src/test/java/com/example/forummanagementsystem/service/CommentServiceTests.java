package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.Helpers;
import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.CommentRepository;
import org.hibernate.id.uuid.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingFormatArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentServiceImpl commentService;

    @BeforeEach
    public void setUp() {
        commentService = new CommentServiceImpl(commentRepository);
    }

    @Test
    public void testCreateCommentByUser() {
        Comment comment = Helpers.createCommentByUser();
        when(commentRepository.create(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment);
        assertEquals(comment, createdComment);
    }
    @Test
    public void testCreateCommentByAdmin() {
        Comment comment = Helpers.createCommentByUser();
        when(commentRepository.create(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment);
        assertEquals(comment, createdComment);
    }

    @Test
    public void testDeleteCommentByAdmin() {
        User admin = Helpers.createMockAdmin();
        Comment comment = Helpers.createCommentByUser();
        comment.setUser(Helpers.createMockAdmin());

        when(commentRepository.getCommentById(comment.getId())).thenReturn(comment);
        when(commentRepository.delete(comment)).thenReturn(comment);

        Comment deletedComment = commentService.delete(admin, comment.getId());

        assertEquals(comment, deletedComment);
        Mockito.verify(commentRepository, Mockito.times(1)).delete(deletedComment);
    }

    @Test
    public void testDeleteCommentByNonAdminUser() {
        User notAdminUser = Helpers.createMockUser();
        Comment comment = Helpers.createCommentByUser();
        when(commentRepository.getCommentById(comment.getId())).thenReturn(comment);
        when(commentRepository.delete(comment)).thenReturn(comment);

        Comment deletedComment = commentService.delete(notAdminUser, comment.getId());

        assertEquals(comment, deletedComment);
        Mockito.verify(commentRepository, Mockito.times(1)).delete(deletedComment);
    }

    @Test
    public void testUpdateCommentByAdmin_Success() {
        User adminUser = Helpers.createMockAdmin();
        Comment comment = Helpers.createCommentByUser();

        when(commentRepository.update(comment)).thenReturn(comment);

        // Act
        Comment updatedComment = commentService.update(comment, adminUser);

        assertEquals(adminUser, updatedComment.getUser());
        Mockito.verify(commentRepository, Mockito.times(1)).update(comment);
    }
    @Test
    public void testGetAllComments() {
        User user = Helpers.createMockAdmin();
        Comment comment1 = Helpers.createCommentByUser();
        Comment comment2 = Helpers.createCommentByUser();
        List<Comment> commentList = Arrays.asList(comment1, comment2);
        when(commentRepository.getAllComments()).thenReturn(commentList);

        List<Comment> result = commentService.getAllComments(user);
        assertEquals(commentList, result);
        Mockito.verify(commentRepository, Mockito.times(1)).getAllComments();

    }
    @Test
    public void testGetUserComments(){
        User user = Helpers.createMockUser();
        Comment comment = Helpers.createCommentByUser();
        Comment comment1 = Helpers.createCommentByUser();
        List<Comment> commentList = Arrays.asList(comment,comment1);

        when(commentRepository.getUserComments(user)).thenReturn(commentList);

        List<Comment> result = commentService.getUserComments(user);
        assertEquals(commentList,result);
        Mockito.verify(commentRepository,Mockito.times(1)).getUserComments(user);
    }

@Test
    public void testGetCommentsById(){
        Comment comment = Helpers.createCommentByUser();
        comment.setId(1);
        when(commentRepository.getCommentById(1)).thenReturn(comment);

        Comment result = commentService.getCommentById(comment.getId());

        assertEquals(comment,result);

        Mockito.verify(commentRepository,Mockito.times(1)).getCommentById(comment.getId());

}

@Test
    public void testPostComments(){

}

}

    //getPostComments


