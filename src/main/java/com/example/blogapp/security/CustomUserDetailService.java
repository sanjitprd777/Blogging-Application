package com.example.blogapp.security;


import com.example.blogapp.entities.User;
import com.example.blogapp.exceptions.ResourceNotFoundException;
import com.example.blogapp.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // loading user from database by username.
        return this.userRepo.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "email: " + username, 0));
    }
}
