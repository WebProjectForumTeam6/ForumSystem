package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.helpers.CommentMapper;
import com.example.forummanagementsystem.helpers.PostMapper;
import com.example.forummanagementsystem.models.Category;
import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.CommentDto;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.service.CategoryService;
import com.example.forummanagementsystem.service.CommentService;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.PostTagService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostMvcController {
    private final PostService postService;
    private final AuthenticationHelper authenticationHelper;
    private final PostMapper postMapper;
    private final PostTagService postTagService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Autowired
    public PostMvcController(PostService postService, AuthenticationHelper authenticationHelper, PostMapper postMapper, PostTagService postTagService, CategoryService categoryService, CommentService commentService, CommentMapper commentMapper) {
        this.postService = postService;
        this.authenticationHelper = authenticationHelper;
        this.postMapper = postMapper;
        this.postTagService = postTagService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public String showSinglePost(@PathVariable int id, Model model, HttpSession httpSession) {
        try {
            User user=authenticationHelper.tryGetCurrentUser(httpSession);
            Post post = postService.getById(id);
            List<Comment> comments = commentService.getPostComments(post);
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);
            model.addAttribute("comment",new Comment());
            model.addAttribute("commentDto", new CommentDto());
            model.addAttribute("loggedIn", user);
            return "PostView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "Error404";
        } catch (AuthorizationException e) {
            Post post = postService.getById(id);
            model.addAttribute("post", post);
            model.addAttribute("comments", post.getComments());
            return "PostView";
        }
    }

    @GetMapping("/new")
    public String showCreatePostView(Model model, HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            model.addAttribute("loggedIn",user);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        model.addAttribute("post", new PostDto());
        return "CreatePostView";
    }

    @PostMapping("/new")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            return "CreatePostView";
        }
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("loggedIn",user);
            Post post = postMapper.fromDtoIn(postDto, user);
            postService.create(post, user);
            postTagService.addTagToPost(postDto.getTags(), user, post);
            return "redirect:/users";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "Error404";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/{postId}/like")
    public String modifyLike(@PathVariable int postId,
                             HttpSession httpSession,
                             @RequestParam("path") String path) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            postService.modifyLike(postId, user);
            return "redirect:/" + path;
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/{postId}/comment")
    public String commentPost(@Valid @ModelAttribute("comment") CommentDto commentDto,
                              BindingResult bindingResult,
                              @PathVariable int postId,
                              HttpSession httpSession) {
        if (bindingResult.hasErrors()){
            return "PostView";
        }
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            Post post = postService.getById(postId);
            Comment comment = commentMapper.fromDto(commentDto, user, post);
            commentService.create(comment);
            return "redirect:/posts/" + postId;
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }


}
