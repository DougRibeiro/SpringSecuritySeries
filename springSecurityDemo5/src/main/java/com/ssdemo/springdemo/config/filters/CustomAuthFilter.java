package com.ssdemo.springdemo.config.filters;

import com.ssdemo.springdemo.config.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authManager;


    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorazationHeader = request.getHeader("Authorization");
        var custAuth = new CustomAuthentication(authorazationHeader, null);

        try {
            Authentication result = authManager.authenticate(custAuth);
            if (result.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(result);
                chain.doFilter(request, response);
            }else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }

        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}