package com.example.devops_revise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class stays {
    @GetMapping("/stays")
    public String getData() {return "Please book hotel ticket with 49% discount";}
}



