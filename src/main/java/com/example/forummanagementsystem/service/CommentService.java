package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.helpers.CommentMapper;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments(User user);
    List<Comment> getUserComments(User user);
    List<Comment> getPostComments(Post post);
    Comment getCommentById(int commentId);
    Comment create(Comment comment);
    Comment update(Comment comment, User user);
    Comment delete(User user, int commentId);
}
