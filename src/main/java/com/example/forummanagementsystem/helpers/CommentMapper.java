package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.CommentDto;
import com.example.forummanagementsystem.service.contracts.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    private final CommentService commentService;

    @Autowired
    public CommentMapper(CommentService commentService) {
        this.commentService = commentService;
    }

    public Comment fromDto(CommentDto commentDto, User creator, Post post) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        comment.setUser(creator);
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }
    public Comment fromDtoUpdate(CommentDto commentDto, int commentId) {
        Comment comment = commentService.getCommentById(commentId);
        comment.setContent(commentDto.getContent());
        return comment;
    }
}
