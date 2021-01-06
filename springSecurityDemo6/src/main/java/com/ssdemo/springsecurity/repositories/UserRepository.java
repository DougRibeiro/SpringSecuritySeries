package com.ssdemo.springsecurity.repositories;

import com.ssdemo.springsecurity.entities.Otp;
import com.ssdemo.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
