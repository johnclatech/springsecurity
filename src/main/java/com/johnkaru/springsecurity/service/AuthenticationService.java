package com.johnkaru.springsecurity.service;

import com.johnkaru.springsecurity.dto.RegisterRequest;
import com.johnkaru.springsecurity.config.JwtService;
import com.johnkaru.springsecurity.dto.AuthenticationRequest;
import com.johnkaru.springsecurity.dto.AuthenticationResponse;
import com.johnkaru.springsecurity.user.Role;
import com.johnkaru.springsecurity.user.User;
import com.johnkaru.springsecurity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest){
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateTokenWithNoExtraClaim(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        var jwtToken = jwtService.generateTokenWithNoExtraClaim(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
