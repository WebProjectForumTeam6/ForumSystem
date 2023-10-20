package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService{
    public static final String ADMIN_OR_CREATOR = "You are not and admin or creator.";
    public static final String NOT_AN_ADMIN = "You are not an admin.";
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments(User user) {
        checkIsAdmin(user);
        return commentRepository.getAllComments();
    }

    @Override
    public List<Comment> getUserComments(User user) {
        return commentRepository.getUserComments(user);
    }

    @Override
    public List<Comment> getPostComments(Post post) {
        return commentRepository.getPostComments(post);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentRepository.getCommentById(commentId);
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.create(comment);
    }

    @Override
    public Comment update(Comment comment, User user) {
        checkAccessPermissions(comment, user);
        return commentRepository.update(comment);
    }

    @Override
    public Comment delete(User user, int commentId) {
        Comment comment=getCommentById(commentId);
        checkAccessPermissions(comment, user);
        return commentRepository.delete(comment);
    }


    private static void checkAccessPermissions(Comment comment, User user) {
        if (!user.isAdmin() && comment.getUser().getId() != user.getId()) {
            throw new AuthorizationException(ADMIN_OR_CREATOR);
        }
    }
    private static void checkIsAdmin(User user) {
        if (!user.isAdmin()){
            throw new AuthorizationException(NOT_AN_ADMIN);
        }
    }
}
