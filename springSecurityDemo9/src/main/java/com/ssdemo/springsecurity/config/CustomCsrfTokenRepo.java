package com.ssdemo.springsecurity.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomCsrfTokenRepo implements CsrfTokenRepository {
    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        CsrfToken token = new DefaultCsrfToken("X-CSRF-TOKEN","_csrf","1234567890");

        return token;
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
        return null;
    }
}
