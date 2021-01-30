package com.ssdemo.springsecurity.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // @RestController = @Controller + @ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainPage() { // Spring MVC Spring will look for the main.html page
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
    // @CrossOrigin("*") // all Origins
    public String test() { // Spring will return the Exact Object.

        System.out.println("The method was called!");
        return "TEST!";
    }
}
