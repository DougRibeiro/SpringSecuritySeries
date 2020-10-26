package com.ssdemo.springSecurityDemo5.config.filters;

import com.ssdemo.springSecurityDemo5.config.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
public class CustomAuthFilter implements Filter {

    @Autowired
    private AuthenticationManager authManager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String authorazationHeader = httpReq.getHeader("Authorization");
        var custAuth = new CustomAuthentication(authorazationHeader, null);
        Authentication result = authManager.authenticate(custAuth);

        if (result.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(result);
            chain.doFilter(request, response);
        }

    }
}
