package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.FilterDto;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeMvcController {
    private final PostService postService;
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;


    @Autowired
    public HomeMvcController(PostService postService, UserService userService, AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }
    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }
    @GetMapping
    public String showHomePage(Model model, FilterDto filterDto, HttpSession httpSession) {
        try{
            authenticationHelper.tryGetCurrentUser(httpSession);
            return "redirect:/users";
        }catch (AuthorizationException e) {
            FilterOptions filterOptions = new FilterOptions(
                    filterDto.getCreatedBy(),
                    filterDto.getTitle(),
                    filterDto.getContent(),
                    filterDto.getCategory(),
                    filterDto.getMinDate(),
                    filterDto.getMaxDate(),
                    filterDto.getSortBy(),
                    filterDto.getSortOrder()
            );
            List<Post> posts = postService.getAll(filterOptions);
            List<User> users = userService.getAll();
            model.addAttribute("topCommented", postService.getTop10MostCommentedPosts());
            model.addAttribute("recentlyAddedPosts", postService.get10MostRecentlyCreatedPosts());
            model.addAttribute("allPosts", posts);
            model.addAttribute("allUsers", users);
            return "HomeView";
        }
    }

    @GetMapping("/about")
    public String showAboutPage(Model model, HttpSession httpSession) {
        try {
            model.addAttribute("loggedIn", authenticationHelper.tryGetCurrentUser(httpSession));
            return "AboutUs";
        }catch (AuthorizationException e){
            return "AboutUs";
        }
    }
    @GetMapping("/contact")
    public String showContactPage(Model model, HttpSession httpSession) {
        try {
            model.addAttribute("loggedIn", authenticationHelper.tryGetCurrentUser(httpSession));
            return "ContactUs";
        }catch (AuthorizationException e){
            return "ContactUs";
        }
    }
}
