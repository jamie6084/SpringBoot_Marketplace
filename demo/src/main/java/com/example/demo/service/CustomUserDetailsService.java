package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //need to do full clear before u log in
        SecurityContextHolder.clearContext();
        
        Optional<User> userOptional = userRepository.findByUsername(username);
    
        if (userOptional.isEmpty()) {


            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("User not found: " + username);


        }
    
        User user = userOptional.get();
        System.out.println("User found: " + user.getUsername());

    
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name().toUpperCase());
    
        return new org.springframework.security.core.userdetails.User(
            
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
    
}


