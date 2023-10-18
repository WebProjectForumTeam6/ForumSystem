package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> getUserComments(User user);
}
