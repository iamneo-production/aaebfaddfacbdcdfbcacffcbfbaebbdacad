package com.example.springapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('USER')")
    public String helloWorld() {
        return "Hello, World!";
    }
}
