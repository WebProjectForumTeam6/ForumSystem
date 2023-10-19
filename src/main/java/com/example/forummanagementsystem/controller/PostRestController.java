package com.example.forummanagementsystem.controller;


import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.helpers.PostMapper;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public PostRestController(PostService postService, AuthenticationHelper authenticationHelper,PostMapper postMapper) {
        this.postService = postService;
        this.authenticationHelper = authenticationHelper;
        this.postMapper=postMapper;
    }


    @GetMapping("/{id}")
    public Post get(@PathVariable int id) {
        try {
            return postService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id){
        try{
            User user = authenticationHelper.tryGetUser(headers);
            postService.delete(id,user);
        } catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        } catch (AuthorizationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
        }
    }
    @PostMapping("/create")
    public Post create(@RequestHeader HttpHeaders headers, @Valid @RequestBody PostDto postDto) {
        try {
            User creator=authenticationHelper.tryGetUser(headers);
            Post post = postMapper.fromDtoIn(postDto, creator);
            postService.create(post, creator);
            return post;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }catch (AuthorizationException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Post> getAll(
            @RequestParam(required = false) User createdBy,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder) {
        FilterOptions filterOptions = new FilterOptions(createdBy,title,content,sortBy,sortOrder);
        return postService.getAll(filterOptions);
    }
//    @GetMapping("/all")
//    public List<Post> getAll(
//            @RequestParam(required = false) User createdBy,
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String content,
//            @RequestParam(required = false) String sortBy) {
//        FilterOptions filterOptions = new FilterOptions(createdBy,title,content,sortBy);
//        return postService.getAll(filterOptions);
//    }

//    @GetMapping("/all")
//    public List<Post> getAllPosts(@RequestBody FilterOptions filterOptions) {
//        return postService.getAll(filterOptions);
//    }



}