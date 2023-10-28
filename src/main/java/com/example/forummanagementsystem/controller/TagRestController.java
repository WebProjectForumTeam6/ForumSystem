package com.example.forummanagementsystem.controller;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.TagDto;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.PostTagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {
    private final AuthenticationHelper authenticationHelper;
    private final PostTagService postTagService;
    private final PostService postService;

    @Autowired
    public TagRestController(AuthenticationHelper authenticationHelper,
                             PostTagService postTagService, PostService postService) {
        this.authenticationHelper = authenticationHelper;
        this.postTagService = postTagService;
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

    @PostMapping()
    public Tag create(@RequestHeader HttpHeaders headers,
                      @Valid @RequestBody TagDto tagDto) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            return postTagService.create(tagDto);
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{tagId}")
    public Tag updateTag(@RequestHeader HttpHeaders headers,
                         @PathVariable int tagId,
                         @RequestBody TagDto tagDto) {

        try {
            User user = authenticationHelper.tryGetUser(headers);
            return postTagService.updateTag(tagId, tagDto);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @DeleteMapping("/tagId")
    public void delete(@RequestHeader HttpHeaders headers,
                       @PathVariable int tagId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            postTagService.delete(tagId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("/{postId}")
    public Post addTagToPost(@RequestHeader HttpHeaders headers,
                             @Valid @RequestBody String tags,
                             @PathVariable int postId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = postService.getById(postId);
            return postTagService.addTagToPost(tags, user, post);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
    @DeleteMapping("/{postId}")
    public Post deleteTagFromPost(@RequestHeader HttpHeaders headers,
                             @Valid @RequestBody String tags,
                             @PathVariable int postId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = postService.getById(postId);
            return postTagService.deleteTagFromPost(tags, user, post);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}









