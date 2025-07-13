package org.example.urlshortner.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.urlshortner.dto.JwtResponse;
import org.example.urlshortner.dto.LoginRequest;
import org.example.urlshortner.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class Authentication {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var token = jwtService.generateJwtToken(request.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }
@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentials(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader){
        return jwtService.validateJwtToken(authHeader.replace("Bearer ", ""));

    }
}
