package com.first.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class First {

    @GetMapping("/greeting")
    public String greeting() {
        System.out.println("call greeting");
        return "Hello, Spring!";
    }
}
