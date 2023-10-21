package com.example.forummanagementsystem.controller;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.PostTagService;
import com.example.forummanagementsystem.service.UserService;
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
                             PostTagService postTagService, UserService userService, PostService postService){
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
public Tag getTagById(@RequestHeader HttpHeaders headers, @PathVariable int id){
        try{
            User user =authenticationHelper.tryGetUser(headers);
            return postTagService.getTagById(id);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
}



}













