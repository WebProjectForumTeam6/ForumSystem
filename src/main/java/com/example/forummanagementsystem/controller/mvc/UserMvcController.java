package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.helpers.ImageHelper;
import com.example.forummanagementsystem.helpers.UserMapper;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.UserFilterOptions;
import com.example.forummanagementsystem.models.dto.FilterDto;
import com.example.forummanagementsystem.models.dto.PathDto;
import com.example.forummanagementsystem.models.dto.UserDtoUpdate;
import com.example.forummanagementsystem.models.dto.UserFilterDto;
import com.example.forummanagementsystem.service.contracts.CategoryService;
import com.example.forummanagementsystem.service.contracts.PostService;
import com.example.forummanagementsystem.service.contracts.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final AuthenticationHelper authenticationHelper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ImageHelper imageHelper;

    public UserMvcController(PostService postService, CategoryService categoryService, AuthenticationHelper authenticationHelper, UserService userService, UserMapper userMapper, ImageHelper imageHelper) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.authenticationHelper = authenticationHelper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.imageHelper = imageHelper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @GetMapping("/about")
    public String showAboutPage(Model model, HttpSession httpSession) {
        try {
            model.addAttribute("loggedIn", authenticationHelper.tryGetCurrentUser(httpSession));
            return "AboutUs";
        } catch (AuthorizationException e) {
            return "AboutUs";
        }
    }

    @GetMapping("/contact")
    public String showContactPage(Model model, HttpSession httpSession) {
        try {
            model.addAttribute("loggedIn", authenticationHelper.tryGetCurrentUser(httpSession));
            return "ContactUs";
        } catch (AuthorizationException e) {
            return "ContactUs";
        }
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
    public String showProfilePage(Model model, HttpSession httpSession) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", user);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        return "ProfileInformation";
    }

    @GetMapping("/update")
    public String showUpdateProfilePage(Model model, HttpSession httpSession) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("userToUpdate", user);
            model.addAttribute("updateDto", userMapper.toDto(user));
            model.addAttribute("loggedIn", user);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        return "UpdateInformation";
    }

    @PostMapping("/update")
    public String updateProfilePage(@ModelAttribute("updateDto") UserDtoUpdate userDtoUpdate, Model model,
                                    HttpSession httpSession) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(httpSession);
            model.addAttribute("userToUpdate", user);
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", user);
            userService.updateUser(user, user, userDtoUpdate);
            return "ProfileInformation";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/admin")
    public String showAdminView(@ModelAttribute("filterDto") UserFilterDto userFilterDto, Model model, HttpSession httpSession){
        try {
            User user=authenticationHelper.tryGetCurrentUser(httpSession);
            UserFilterOptions userFilterOptions=new UserFilterOptions(userFilterDto.getUsername(),
                    userFilterDto.getFirstName(),
                    userFilterDto.getEmail());
            List<User> users=userService.get(userFilterOptions);
            model.addAttribute("users", users);
            model.addAttribute("loggedIn", user);
            model.addAttribute("filterDto", userFilterDto);
            return "AdminView";
        } catch (AuthorizationException e){
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/{userId}/makeAdmin")
    public String makeAdmin(@PathVariable int userId, HttpSession httpSession){
        try {
            User user=authenticationHelper.tryGetCurrentUser(httpSession);
            userService.makeAdmin(user, userService.getById(userId));
        } catch (AuthorizationException e){
            return "redirect:/auth/login";
        }
        return "redirect:/users/admin";
    }

    @PostMapping("/{userId}/block")
    public String block(@PathVariable int userId, HttpSession httpSession){
        try {
            User user=authenticationHelper.tryGetCurrentUser(httpSession);
            User userToBlock=userService.getById(userId);
            if (!userToBlock.isBlocked()) {
                userService.block(user, userToBlock);
            } else {
                userService.unblock(user, userToBlock);
            }
        } catch (AuthorizationException e){
            return "redirect:/auth/login";
        }
        return "redirect:/users/admin";
    }

    @PostMapping("/{userId}/picture")
    public String addPicture(@RequestParam("file") MultipartFile file, @PathVariable int userId, HttpSession httpSession){
        try {
            User user=authenticationHelper.tryGetCurrentUser(httpSession);
            String url= imageHelper.uploadImage(file);
            userService.addProfilePhoto(userService.getById(userId), url);
            return "redirect:/users/update";
        }catch (AuthorizationException e){
            return "redirect:/auth/login";
        } catch (IOException e){
            return "Error404";
        }
    }
}
