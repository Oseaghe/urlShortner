package org.example.urlshortner.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Integer id;
    private String name;
    private String email;
}
