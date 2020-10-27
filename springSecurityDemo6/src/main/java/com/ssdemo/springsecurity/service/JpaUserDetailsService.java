package com.ssdemo.springsecurity.service;

import com.ssdemo.springsecurity.entities.User;
import com.ssdemo.springsecurity.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        var userOptional = userRepository.findUserByUsername(s);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User was not found!"));

        //return user;
        return null;
    }
}
