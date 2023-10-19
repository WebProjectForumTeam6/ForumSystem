package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.helpers.CommentMapper;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> getUserComments(User user);
    List<Comment> getPostComments(Post post);
    Comment create(Comment comment);
}
