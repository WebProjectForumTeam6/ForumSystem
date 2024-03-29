package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.helpers.CommentMapper;
import com.example.forummanagementsystem.helpers.PostMapper;
import com.example.forummanagementsystem.models.*;
import com.example.forummanagementsystem.models.dto.CommentDto;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.service.contracts.CategoryService;
import com.example.forummanagementsystem.service.contracts.CommentService;
import com.example.forummanagementsystem.service.contracts.PostService;
import com.example.forummanagementsystem.service.contracts.PostTagService;
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
import java.util.stream.Collectors;

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
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            Post post = postService.getById(id);
            List<Comment> comments = commentService.getPostComments(post);
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);
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
            model.addAttribute("loggedIn", user);
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
            model.addAttribute("loggedIn", authenticationHelper.tryGetCurrentUser(httpSession));
            return "CreatePostView";
        }
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("loggedIn", user);
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
    public String commentPost(@Valid @ModelAttribute("commentDto") CommentDto commentDto,
                              BindingResult bindingResult,
                              @PathVariable int postId,
                              Model model,
                              HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            Post post = postService.getById(postId);
            List<Comment> comments = commentService.getPostComments(post);
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);
            model.addAttribute("loggedIn", user);
            return "PostView";
        }
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            Post post = postService.getById(commentDto.getPostId());
            Comment comment = commentMapper.fromDto(commentDto, user, post);
            commentService.create(comment);
            return "redirect:/posts/" + commentDto.getPostId();
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/comment/{commentId}/update")
    public String showUpdateCommentView(Model model, @PathVariable int commentId, CommentDto commentDto, HttpSession httpSession) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            Comment comment=commentService.getCommentById(commentId);
            model.addAttribute("loggedIn", user);
            model.addAttribute("commentToUpdate", commentService.getCommentById(commentId));
            model.addAttribute("user", user);
            commentDto.setContent(comment.getContent());
            model.addAttribute("commentDto", commentDto);
            model.addAttribute("comment", comment);
            return "UpdateCommentView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/comment/{commentId}/update")
    public String updateComment(@Valid @ModelAttribute("updateDto") CommentDto commentDto, @PathVariable int commentId, Model model, HttpSession httpSession) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            Comment comment=commentService.getCommentById(commentId);
            model.addAttribute("loggedIn", user);
            model.addAttribute("commentToUpdate", comment);
            model.addAttribute("user", user);
            comment.setContent(commentDto.getContent());
            commentService.update(comment, user);
            return "redirect:/posts/" + comment.getPost().getId();
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }
    @PostMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable int commentId, HttpSession httpSession) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            Comment comment=commentService.getCommentById(commentId);
            commentService.delete(user, commentId);
            return "redirect:/posts/" + comment.getPost().getId();
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }
    @PostMapping("/{postId}/delete")
    public String deletePost(HttpSession httpSession,
                             @PathVariable int postId,
                             @RequestParam("path") String path) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            postService.delete(postId, user);
            return "redirect:/users" + path;
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/{postId}/update")
    public String showUpdatePostPage(@PathVariable int postId, PostDto postDto,
                                     HttpSession httpSession, Model model) {
        try {
            Post post = postService.getById(postId);
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("loggedIn", user);
            postDto.setTitle(post.getTitle());
            postDto.setContent(post.getContent());
            postDto.setCategoryId(post.getCategory().getId());
            postDto.setTags(post.getTags().stream().map(Tag::getContent).collect(Collectors.joining(",")));
            model.addAttribute("postDto", postDto);
            return "UpdatePostView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/{postId}/update")
    public String updatePost(@PathVariable int postId, PostDto postDto,
                             HttpSession httpSession, Model model) {
        try {
            Post post = postService.getById(postId);
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            postTagService.deleteAllTagsFromPost(user, post);
            model.addAttribute("loggedIn", user);
            model.addAttribute("postDto", postDto);
            model.addAttribute("post", post);
            model.addAttribute("commentDto", new CommentDto());
            model.addAttribute("comments", post.getComments());
            postService.update(postDto, user, postId);
            postTagService.addTagToPost(postDto.getTags(), user, post);
            return "PostView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }
}
