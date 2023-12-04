package com.johnkaru.springsecurity.controller;

import com.johnkaru.springsecurity.dto.AuthenticationRequest;
import com.johnkaru.springsecurity.dto.AuthenticationResponse;
import com.johnkaru.springsecurity.dto.RegisterRequest;
import com.johnkaru.springsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> responseResponseEntity(@RequestBody RegisterRequest registerRequest){

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> responseResponseEntity(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
