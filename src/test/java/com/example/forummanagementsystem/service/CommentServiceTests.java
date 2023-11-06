package com.example.forummanagementsystem.service;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static com.example.forummanagementsystem.Helpers.*;
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
        Comment comment = createCommentByUser();
        when(commentRepository.create(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment);
        assertEquals(comment, createdComment);
    }
    @Test
    public void testCreateCommentByAdmin() {
        Comment comment = createCommentByUser();
        when(commentRepository.create(comment)).thenReturn(comment);

        Comment createdComment = commentService.create(comment);
        assertEquals(comment, createdComment);
    }

    @Test
    public void testDeleteCommentByAdmin() {
        User admin = createMockAdmin();
        Comment comment = createCommentByUser();
        comment.setUser(createMockAdmin());

        when(commentRepository.getCommentById(comment.getId())).thenReturn(comment);
        when(commentRepository.delete(comment)).thenReturn(comment);

        Comment deletedComment = commentService.delete(admin, comment.getId());

        assertEquals(comment, deletedComment);
        Mockito.verify(commentRepository, Mockito.times(1)).delete(deletedComment);
    }

    @Test
    public void testDeleteCommentByNonAdminUser() {
        User notAdminUser = createMockUser();
        Comment comment = createCommentByUser();
        when(commentRepository.getCommentById(comment.getId())).thenReturn(comment);
        when(commentRepository.delete(comment)).thenReturn(comment);

        Comment deletedComment = commentService.delete(notAdminUser, comment.getId());

        assertEquals(comment, deletedComment);
        Mockito.verify(commentRepository, Mockito.times(1)).delete(deletedComment);
    }

    @Test
    public void testUpdateCommentByAdmin_Success() {
        User adminUser = createMockAdmin();
        Comment comment = createCommentByUser();

      commentService.update(comment,adminUser);

        Mockito.verify(commentRepository, Mockito.times(1))
                .update(comment);
    }
    @Test
    public void testGetAllComments() {
        User user = createMockAdmin();
        Comment comment1 = createCommentByUser();
        Comment comment2 = createCommentByUser();
        List<Comment> commentList = Arrays.asList(comment1, comment2);
        when(commentRepository.getAllComments()).thenReturn(commentList);

        List<Comment> result = commentService.getAllComments(user);
        assertEquals(commentList, result);
        Mockito.verify(commentRepository, Mockito.times(1)).getAllComments();

    }
    @Test
    public void testGetUserComments(){
        User user = createMockUser();
        Comment comment = createCommentByUser();
        Comment comment1 = createCommentByUser();
        List<Comment> commentList = Arrays.asList(comment,comment1);

        when(commentRepository.getUserComments(user)).thenReturn(commentList);

        List<Comment> result = commentService.getUserComments(user);
        assertEquals(commentList,result);
        Mockito.verify(commentRepository,Mockito.times(1)).getUserComments(user);
    }

@Test
    public void testGetCommentsById(){
        Comment comment = createCommentByUser();
        comment.setId(1);
        when(commentRepository.getCommentById(1)).thenReturn(comment);

        Comment result = commentService.getCommentById(comment.getId());

        assertEquals(comment,result);

        Mockito.verify(commentRepository,Mockito.times(1)).getCommentById(comment.getId());

}

@Test
    public void testPostComments(){
        Comment comment = createCommentByUser();
        Comment comment1 = createCommentByUser();
        Post post = createMockPost();
        List<Comment> postsComments = Arrays.asList(comment,comment1);
    when(commentRepository.getPostComments(post)).thenReturn(postsComments);

    List<Comment> result = commentService.getPostComments(post);

    assertEquals(postsComments,result);

    Mockito.verify(commentRepository,Mockito.times(1)).getPostComments(post);
}

}




