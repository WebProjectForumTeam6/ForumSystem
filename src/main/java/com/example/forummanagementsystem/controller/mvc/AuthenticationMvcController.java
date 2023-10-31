package com.example.forummanagementsystem.controller.mvc;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.helpers.AuthenticationHelper;
import com.example.forummanagementsystem.helpers.UserMapper;
import com.example.forummanagementsystem.models.dto.LoginDto;
import com.example.forummanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationMvcController {
    private final AuthenticationHelper authenticationHelper;
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthenticationMvcController(AuthenticationHelper authenticationHelper, UserService userService, UserMapper userMapper) {
        this.authenticationHelper = authenticationHelper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("login", new LoginDto());
        return "LoginView";
    }

    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("login") LoginDto loginDto,
                              BindingResult bindingResult,
                              HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "LoginView";
        }
        try {
            authenticationHelper.verifyAuthentication(loginDto.getUsername(), loginDto.getPassword());
            httpSession.setAttribute("currentUser", loginDto.getUsername());
            return "redirect:/";
        }catch (AuthorizationException e) {
            bindingResult.rejectValue("username", "auth_error", e.getMessage());
            return "LoginView";
        }

    }
}

