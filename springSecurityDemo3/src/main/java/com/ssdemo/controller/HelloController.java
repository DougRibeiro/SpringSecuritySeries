package com.ssdemo.controller;

import com.ssdemo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {

    @Autowired
    private JdbcUserDetailsManager userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsService.createUser(user);
    }
}
