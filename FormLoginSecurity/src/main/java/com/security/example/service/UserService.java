package com.security.example.service;

import com.security.example.entity.User;

public interface UserService {

    User createUser(User user);

    User getUserById(User user);

    User getUserByUsername(String username);

    boolean existByUsername(String username);

}
