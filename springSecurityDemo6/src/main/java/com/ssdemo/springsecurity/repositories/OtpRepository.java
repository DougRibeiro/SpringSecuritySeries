package com.ssdemo.springsecurity.repositories;

import com.ssdemo.springsecurity.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp,Integer> {
}
