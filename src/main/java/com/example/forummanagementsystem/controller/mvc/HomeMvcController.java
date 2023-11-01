package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {
    private final PostService postService;

    @Autowired
    public HomeMvcController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("topCommented", postService.getTop10MostCommentedPosts());
        model.addAttribute("recentlyAddedPosts", postService.getTop10MostCommentedPosts());
        return "HomeView";
    }
}
