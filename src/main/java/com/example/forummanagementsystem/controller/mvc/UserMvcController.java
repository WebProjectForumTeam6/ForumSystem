package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.FilterDto;
import com.example.forummanagementsystem.models.dto.PathDto;
import com.example.forummanagementsystem.service.PostService;
import com.example.forummanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final PostService postService;
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    public UserMvcController(PostService postService, UserService userService, AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public String showUserPage(Model model, FilterDto filterDto, HttpSession httpSession) {
        FilterOptions filterOptions = new FilterOptions(
                filterDto.getCreatedBy(),
                filterDto.getTitle(),
                filterDto.getContent(),
                filterDto.getCategory(),
                filterDto.getSortBy(),
                filterDto.getSortOrder()
        );
        List<Post> posts = postService.getAll(filterOptions);
        model.addAttribute("allPosts", posts);
        model.addAttribute("pathDto", new PathDto());
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("loggedIn", user);
            return "UserPage";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
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
