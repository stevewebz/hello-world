package com.gymfitness.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
public class HomeController {
    @GetMapping(value="/")
    public String home() {
        return "Hello.";
    }
}