package com.example.forummanagementsystem.controller;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.PostTagService;
import com.example.forummanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {
    private final AuthenticationHelper authenticationHelper;
    private final PostTagService postTagService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public TagRestController(AuthenticationHelper authenticationHelper,
                             PostTagService postTagService, UserService userService, PostService postService) {
        this.authenticationHelper = authenticationHelper;
        this.postTagService = postTagService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return postTagService.getAllTags();
    }


    @GetMapping("/{id}")
    public Tag getTagById(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            return postTagService.getTagById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/{tagId}")
    public PostTag create(@RequestHeader HttpHeaders headers,
                          @Valid @RequestBody PostTag postTag,
                          @PathVariable int postId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = postService.getById(postId);
            postTagService.create(postTag, user);
            return postTag;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @DeleteMapping("/{postIdForTag}")
    public void deleteAllTagsForPost(@RequestHeader HttpHeaders headers,
                                     @PathVariable int postIdForTag) {

        try {
            User user = authenticationHelper.tryGetUser(headers);
            postTagService.deleteAllTagsForPost(postIdForTag);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PutMapping("/{tagId}")
    public Tag updateTag(@RequestHeader HttpHeaders headers,
                         @PathVariable int tagId,
                         @RequestBody String content) {

        try {
            User user = authenticationHelper.tryGetUser(headers);
            return postTagService.updateTag(tagId, content, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}









