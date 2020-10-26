package com.ssdemo.springSecurityDemo5.config.providers;

import com.ssdemo.springSecurityDemo5.config.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Value("${key}")
    private String key;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var authKey = authentication.getName();
        if (authKey.equals(key)) {
            var newAuth = new CustomAuthentication(null,null,null);
            return newAuth;
        } else {

            throw new BadCredentialsException("Wrong Credentials!");
        }
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return CustomAuthentication.class.equals(authClass);
    }
}
