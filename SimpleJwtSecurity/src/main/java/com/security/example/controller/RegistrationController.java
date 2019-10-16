package com.security.example.controller;

import com.security.example.entity.User;
import com.security.example.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/signup")
    @PreAuthorize("permitAll()")
    public String registrationPage() {

        return "registration";
    }

    @PostMapping(value = "/signup")
    @PreAuthorize("permitAll()")
    public String registration(@ModelAttribute User user) {

        userService.createUser(user);

        return "redirect:/signin";
    }
}
