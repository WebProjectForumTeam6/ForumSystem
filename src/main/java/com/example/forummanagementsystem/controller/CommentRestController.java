package com.example.forummanagementsystem.controller;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.helpers.CommentMapper;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.CommentDto;
import com.example.forummanagementsystem.service.contracts.CommentService;
import com.example.forummanagementsystem.service.contracts.PostService;
import com.example.forummanagementsystem.service.contracts.UserService;
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
    private final UserService userService;
    private final PostService postService;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentRestController(AuthenticationHelper authenticationHelper,
                                 CommentService commentService, UserService userService,
                                 PostService postService, CommentMapper commentMapper) {
        this.authenticationHelper = authenticationHelper;
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            return commentService.getAllComments(user);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public List<Comment> getUserComments(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            User requestComments = userService.getById(id);
            return commentService.getUserComments(requestComments);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/post/{id}")
    public List<Comment> getPostComments(@RequestHeader HttpHeaders headers,
                                         @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = postService.getById(id);
            return commentService.getPostComments(post);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/{postId}")
    public Comment create(@RequestHeader HttpHeaders headers,
                          @Valid @RequestBody CommentDto commentDto,
                          @PathVariable int postId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = postService.getById(postId);
            Comment comment = commentMapper.fromDto(commentDto, user, post);
            return commentService.create(comment);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{commentId}")
    public Comment update(@RequestHeader HttpHeaders headers,
                          @Valid @RequestBody CommentDto commentDto,
                          @PathVariable int commentId){
        try {
            User user=authenticationHelper.tryGetUser(headers);
            Comment comment=commentMapper.fromDtoUpdate(commentDto, commentId);
            return commentService.update(comment, user);
        } catch (AuthorizationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{commentId}")
    public Comment delete(@RequestHeader HttpHeaders headers,
                          @PathVariable int commentId){
        try {
            User user=authenticationHelper.tryGetUser(headers);
            return commentService.delete(user, commentId);
        }catch (AuthorizationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

