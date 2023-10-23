package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.Helpers;
import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        User user = Helpers.createMockUser();
        Comment comment = Helpers.createCommentByUser();
        when(commentRepository.create(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment);
        assertEquals(comment, createdComment);
    }
    @Test
    public void testCreateCommentByAdmin(){}

    @Test
    public void testDeleteCommentByAdmin() {
        User admin = Helpers.createMockAdmin();
        Comment comment = Helpers.createCommentByUser();
        comment.setUser(Helpers.createMockAdmin());

        when(commentRepository.getCommentById(comment.getId())).thenReturn(comment);
        when(commentRepository.delete(comment)).thenReturn(comment);

        Comment deletedComment = commentService.delete(admin, comment.getId());

        assertEquals(comment, deletedComment);
        Mockito.verify(commentRepository,Mockito.times(1)).delete(deletedComment);
    }

    @Test
    public void testDeleteCommentByNonAdminUser() {
        User notAdminUser = Helpers.createMockUser();
        Comment comment = Helpers.createCommentByUser();
        when(commentRepository.getCommentById(comment.getId())).thenReturn(comment);
        when(commentRepository.delete(comment)).thenReturn(comment);

        Comment deletedComment = commentService.delete(notAdminUser,comment.getId());

        assertEquals(comment,deletedComment);
        Mockito.verify(commentRepository,Mockito.times(1)).delete(deletedComment);
    }

//    @Test
//    public void testUpdateCommentByAdmin() {
//        User adminUser = new User();
//        Comment comment = new Comment();
//
//        // Mock the user to be an admin
//        when(commentRepository.getCommentById(comment.getId())).thenReturn(comment);
//
//        Comment updatedComment = new Comment();
//        when(commentRepository.update(comment)).thenReturn(updatedComment);
//
//        Comment result = commentService.update(comment, adminUser);
//
//        // Verify that the comment was updated successfully
//        assertEquals(updatedComment, result);
//    }
}