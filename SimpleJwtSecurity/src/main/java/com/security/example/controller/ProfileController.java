package com.security.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping(value = "/profile")
    @PreAuthorize("hasRole('USER')")
    public String profilePage() {

        return "user";
    }
}
