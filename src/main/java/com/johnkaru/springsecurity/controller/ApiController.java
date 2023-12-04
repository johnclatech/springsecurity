package com.johnkaru.springsecurity.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/controller")

public class ApiController {

    @GetMapping("/")
    public ResponseEntity<String> homePage(){
        return ResponseEntity.ok("Hello from Secured EndPoint!!");
    }
}
