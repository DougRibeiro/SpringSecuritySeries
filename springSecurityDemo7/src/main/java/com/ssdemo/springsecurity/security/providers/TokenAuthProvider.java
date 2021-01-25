package com.ssdemo.springsecurity.security.providers;

import com.ssdemo.springsecurity.security.authentications.TokenAuthentication;
import com.ssdemo.springsecurity.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String token = authentication.getName();
        boolean exists = tokenManager.contains(token);

        if(exists){
            return new TokenAuthentication(token, null ,null);
        }
        throw new BadCredentialsException("Invalid Token!");

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
