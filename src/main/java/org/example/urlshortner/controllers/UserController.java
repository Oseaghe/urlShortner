package org.example.urlshortner.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.urlshortner.dto.LoginRequest;
import org.example.urlshortner.dto.RegisterUserRequest;
import org.example.urlshortner.entities.User;
import org.example.urlshortner.mapper.UserMapper;
import org.example.urlshortner.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/urlusers")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("email", "Email is already in use!"));
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ResponseEntity.ok(user);
    }
}