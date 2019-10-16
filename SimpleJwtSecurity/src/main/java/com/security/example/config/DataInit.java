package com.security.example.config;

import com.security.example.entity.User;
import com.security.example.entity.enums.Authority;
import com.security.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit implements ApplicationRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Set<Authority> authorities = new HashSet<Authority>() {{
        add(Authority.ROLE_USER);
    }};

    public DataInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.existsByUsername("1")) {
            return;
        }

        User user = new User();
        user.setFirstName("1");
        user.setLastName("1");
        user.setUsername("1");
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode("1"));
        userRepository.save(user);
    }
}
