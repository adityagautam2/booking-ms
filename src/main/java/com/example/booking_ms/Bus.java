package com.example.booking_ms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bus {
    @GetMapping("/Bus")
    public String getData() {
        return "Please book Bus ticket with 50% discount"; }
}
