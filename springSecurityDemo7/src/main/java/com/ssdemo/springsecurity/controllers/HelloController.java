package com.ssdemo.springsecurity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    @Async
    public String hello(Authentication authentication) {

        Runnable r = ()->{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            System.out.println("from the thread = "+auth.getName());
        };
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(r);
        service.shutdown();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.getName());
        System.out.println(authentication.toString());

        return "hello! " + authentication.getName();
    }


}
