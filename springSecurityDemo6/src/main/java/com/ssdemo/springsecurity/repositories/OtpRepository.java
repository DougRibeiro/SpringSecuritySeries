package com.ssdemo.springsecurity.repositories;

import com.ssdemo.springsecurity.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Optional<Otp> findOtpByUsername(String username);
}
