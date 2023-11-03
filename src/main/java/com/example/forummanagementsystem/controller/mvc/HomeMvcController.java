package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.FilterDto;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeMvcController {
    private final PostService postService;
    private final UserService userService;


    @Autowired
    public HomeMvcController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String showHomePage(Model model, FilterDto filterDto) {
        FilterOptions filterOptions=new FilterOptions(
                filterDto.getCreatedBy(),
                filterDto.getTitle(),
                filterDto.getContent(),
                filterDto.getSortBy(),
                filterDto.getSortOrder()
        );
        List<Post> posts=postService.getAll(filterOptions);
        List<User> users=userService.getAll();
        model.addAttribute("topCommented", postService.getTop10MostCommentedPosts());
        model.addAttribute("recentlyAddedPosts", postService.get10MostRecentlyCreatedPosts());
        model.addAttribute("allPosts", posts);
        model.addAttribute("allUsers", users);
        return "HomeView";
    }
}
