package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.FilterDto;
import com.example.forummanagementsystem.models.dto.PathDto;
import com.example.forummanagementsystem.service.CategoryService;
import com.example.forummanagementsystem.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final AuthenticationHelper authenticationHelper;

    public UserMvcController(PostService postService, CategoryService categoryService, AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.authenticationHelper = authenticationHelper;
    }
    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }
    @GetMapping
    public String showUserPage(@ModelAttribute("filter") FilterDto filterDto, Model model, HttpSession httpSession) {
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
        model.addAttribute("filterOptions", filterDto);
        model.addAttribute("allPosts", posts);
        model.addAttribute("pathDto", new PathDto());
        model.addAttribute("categories", categoryService.getAll());
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("loggedIn", user);
            return "UserPage";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }
@GetMapping("/profile")
public String showProfilePage(Model model,HttpSession httpSession){
        try {

            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("userPosts", user);
        } catch (AuthorizationException e){
            return "redirect:/auth/login";
        }
        return "ProfileInformation";
}

    @GetMapping("/about")
    public String showAboutPage() {
        return "AboutUs";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "ContactUs";
    }
}
