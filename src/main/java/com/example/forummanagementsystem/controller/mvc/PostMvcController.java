package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostMvcController {
    private final PostService postService;

    @Autowired
    public PostMvcController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public String showSinglePost(@PathVariable int id, Model model) {
        try {
            Post post=postService.getById(id);
            model.addAttribute("post", post);
            return "PostView";
        } catch (EntityNotFoundException e){
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "Error404";
        }
    }
}
