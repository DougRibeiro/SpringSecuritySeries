package com.ssdemo.springsecurity.security.providers;

import com.ssdemo.springsecurity.repositories.OtpRepository;
import com.ssdemo.springsecurity.security.authentications.OtpAuthentication;
import com.ssdemo.springsecurity.security.authentications.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtpAuthenticationProvider  implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();
        var o = otpRepository.findOtpByUsername(username);

        if (o.isPresent()) {
            return new UsernamePasswordAuthentication(username, otp, List.of(() -> "read"));
        }
        throw new BadCredentialsException("Wrong username password!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.equals(aClass);
    }
}