package com.propsur.api.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.propsur.api.project.entity.TmUsers;
import com.propsur.api.project.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TmUsers user = userRepo.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Replace with actual roles if needed
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                //.roles("USER") // You can dynamically assign roles based on user data
                //.accountLocked(!user.isActive()) // Account status logic based on entity
                .build();
    }
}
