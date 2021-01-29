package com.ssdemo.springsecurity.security.filter;

import com.ssdemo.springsecurity.entities.Otp;
import com.ssdemo.springsecurity.repositories.OtpRepository;
import com.ssdemo.springsecurity.security.authentications.OtpAuthentication;
import com.ssdemo.springsecurity.security.authentications.UsernamePasswordAuthentication;
import com.ssdemo.springsecurity.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        var username = httpServletRequest.getHeader("username");
        var password = httpServletRequest.getHeader("password");
        var otp = httpServletRequest.getHeader("otp");

        if (otp == null) {
            Authentication auth = new UsernamePasswordAuthentication(username, password);
            authenticationManager.authenticate(auth);
            Otp otpObject = new Otp();
            otpObject.setUsername(username);
            otpObject.setOtp(String.valueOf(new Random().nextInt(9999) + 1000));
            otpRepository.save(otpObject);

        } else {
            Authentication auth = new OtpAuthentication(username, otp);
            authenticationManager.authenticate(auth);

            final String token = UUID.randomUUID().toString();
            tokenManager.add(token);
            httpServletResponse.setHeader("Authorization", token);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}