package com.example.booking_ms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Train {
    @GetMapping("/Train")
    public String getData() {
        return "Please book Train ticket with 30% discount"; }
}
