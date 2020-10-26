package com.ssdemo.springSecurityDemo5.config.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class CustomAuthentication extends UsernamePasswordAuthenticationToken {


    public CustomAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
    public CustomAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
