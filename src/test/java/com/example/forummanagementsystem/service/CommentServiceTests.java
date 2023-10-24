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
        User user = Helpers.createMockUser();
        Comment comment = Helpers.createCommentByUser();
        when(commentRepository.create(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment);
        assertEquals(comment, createdComment);
    }
    @Test
    public void testCreateCommentByAdmin(){
        User user = Helpers.createMockUser();
        Comment comment = Helpers.createCommentByUser();
        when(commentRepository.create(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment);
        assertEquals(comment,createdComment);
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

    //getAllComments
    //getUserComments
    //getPostComments
    //getCommentsById

}