package com.url.shortify.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.url.shortify.models.User;
import com.url.shortify.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)  // Load user from Database 
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

// Convert user to UserDetails Object so that Spring Security can understand
        return UserDetailsImpl.build(user);
    }
}