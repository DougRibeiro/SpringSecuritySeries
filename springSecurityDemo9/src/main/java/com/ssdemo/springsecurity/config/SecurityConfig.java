package com.ssdemo.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        //  http.csrf().disable();  //Not Recommentded!

        http.csrf(c -> {
            c.ignoringAntMatchers("/csrfdisable/**");
            c.csrfTokenRepository(new CustomCsrfTokenRepo());
        });

        http.addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class);
    }
}
