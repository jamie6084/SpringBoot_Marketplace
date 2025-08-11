package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // need to actually see password in db

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, Role role) {
        if(userRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("Username already exists!");
        }
        
        User user = new User();
        user.setUsername(username);
        //prob shouldnt if real access.
        user.setPassword(password);
        user.setRole(role);
        return userRepository.save(user);
    }
}

