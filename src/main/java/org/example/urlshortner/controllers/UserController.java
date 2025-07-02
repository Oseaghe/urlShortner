package org.example.urlshortner.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.urlshortner.dto.RegisterUserRequest;
import org.example.urlshortner.mapper.UserMapper;
import org.example.urlshortner.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/urlusers")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @PostMapping
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder ){
        if(userRepository.existsByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(Map.of("email", "Email is already in use!"));
        }
        var user = userMapper.toEntity(request);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
}
