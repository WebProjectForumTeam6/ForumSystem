package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.getAllComments();
    }

    @Override
    public List<Comment> getUserComments(User user) {
        return commentRepository.getUserComments(user);
    }
}
