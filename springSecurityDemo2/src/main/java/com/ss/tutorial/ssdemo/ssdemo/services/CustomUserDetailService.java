package com.ss.tutorial.ssdemo.ssdemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.tutorial.ssdemo.ssdemo.entity.CustomUserDetails;
import com.ss.tutorial.ssdemo.ssdemo.entity.User;
import com.ss.tutorial.ssdemo.ssdemo.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        Optional<User> userOptional = userRepository.findUserByUserName(s);
        User response = userOptional.orElseThrow(()-> new UsernameNotFoundException("User not found!"));
        return new CustomUserDetails(response);
    }
}
