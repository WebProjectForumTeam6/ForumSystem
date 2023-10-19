package com.example.forummanagementsystem.controller;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    private final AuthenticationHelper authenticationHelper;
    private final CommentService commentService;
    @Autowired
    public CommentRestController(AuthenticationHelper authenticationHelper, CommentService commentService) {
        this.authenticationHelper = authenticationHelper;
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestHeader HttpHeaders headers){
        try {
            User user=authenticationHelper.tryGetUser(headers);
            return commentService.getAllComments();
        } catch (AuthorizationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    @GetMapping("/user/{id}")
    public List<Comment> getUserComments(@RequestHeader HttpHeaders headers, @PathVariable int id){
        try {
            User user= authenticationHelper.tryGetUser(headers);
            return commentService.getUserComments(user);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
//    @PostMapping("/create")
//    public Comment create(@RequestHeader HttpHeaders headers, @Valid @RequestBody){
//
//    }
}
