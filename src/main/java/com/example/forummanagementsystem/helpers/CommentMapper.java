package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.CommentDto;
import com.example.forummanagementsystem.service.CommentService;

public class CommentMapper {
    private final CommentService commentService;

    public CommentMapper(CommentService commentService) {
        this.commentService = commentService;
    }
    public Comment fromDto(CommentDto commentDto, User creator, Post post){
        Comment comment=new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        comment.setUser(creator);
        return comment;
    }
}
