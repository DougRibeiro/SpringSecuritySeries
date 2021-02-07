package com.ssdemo.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /*
    Ways in wich the client can obtain the token:
    -> authorization_code pkce
    -> password(depricated but widely used)
    -> client_credentials
    -> refresh_token
    -> implicit(depricated)

     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret("secrect1")
                .scopes("read")
                .authorizedGrantTypes("password")
        .and()
                .withClient("client2")
                .secret("secrect2")
                .scopes("read")
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:9090");

        //to obtain the authorization_code
        //http://localhost:8080/oauth/authorize?response_type=code&client_id=client2&scope=read

        //to obtain the token
        //http://localhost:8080/oauth/token?grant_type=authorization_code&username=Doug&password=12345&scope=read&code=8h8NUl
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
