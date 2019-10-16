package com.security.example.controller;

import com.security.example.security.JwtProvider;
import com.security.example.security.JwtResponse;
import com.security.example.security.dto.UserLogIn;
import com.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LogInController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping(value = "/signin")
    @PreAuthorize("permitAll()")
    public String loginPage() {

        return "login";
    }

    @PostMapping("/signin")
    @PreAuthorize("permitAll()")
    public String authenticateUser(@Valid @ModelAttribute UserLogIn userLogIn) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogIn.getUsername(),
                        userLogIn.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        System.out.println(jwt);
        new JwtResponse(jwt);
        return "redirect:/profile";
    }



}
