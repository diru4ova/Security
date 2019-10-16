package com.security.example.service.impl;

import com.security.example.entity.User;
import com.security.example.entity.enums.Authority;
import com.security.example.repository.UserRepository;
import com.security.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private Set<Authority> authorities = new HashSet<Authority>() {{
        add(Authority.ROLE_USER);
    }};

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {

        if (existByUsername(user.getUsername())) {
            throw new RuntimeException("This username already exist");
        }
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User getUserById(User user) {

        return userRepository.findById(user.getUser_id()).orElseThrow(
                () -> new RuntimeException("User does not exist by id:" + user.getUser_id()));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
