package com.eventbooking.eventbookingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppHealthController {

    @GetMapping("/")
    public String home() {
        return "Event Booking System is up and running!!!";
    }

    @GetMapping("/test")
    public String test(){
        return "Test!!";
    }

}