package com.ss.tutorial.ssdemo.ssdemo.repository;

import com.ss.tutorial.ssdemo.ssdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findUserByUserName(String userName);

}
