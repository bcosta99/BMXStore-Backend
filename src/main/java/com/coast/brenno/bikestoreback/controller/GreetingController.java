package com.coast.brenno.bikestoreback.controller;

import com.coast.brenno.bikestoreback.service.greeting.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingService service;

    /**
     * GETTING A GREET TO TEST
     */
    @GetMapping(path = "/greeting", produces = "application/json")
    public ResponseEntity getGreeting() {
        String greeting = service.getGreeting();
        return ResponseEntity.ok(greeting);
    }

    /**
     * GETTING A GREET TO TEST RESPONDEBODY
     */
    @ResponseBody
    @GetMapping(path = "/test", produces = "application/json")
    public String getGreeting2() {
        String greeting = service.getGreeting();
        return greeting;
    }
}