package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface CommentRepository {
    List<Comment> getAllComments();
    List<Comment> getUserComments(User user);
}
