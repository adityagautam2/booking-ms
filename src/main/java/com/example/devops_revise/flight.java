package com.example.devops_revise;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class flight {
    @GetMapping("/flight")
public String getData() {return "Please book flights ticket with 30% discount";}
}


