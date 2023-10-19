package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.CommentDto;

import java.util.List;

public interface CommentRepository {
    List<Comment> getAllComments();
    List<Comment> getUserComments(User user);
    List<Comment> getPostComments(Post post);
    Comment create(Comment comment);
}
