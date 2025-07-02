package org.example.urlshortner.mapper;

import org.example.urlshortner.dto.RegisterUserRequest;
import org.example.urlshortner.dto.UserDto;
import org.example.urlshortner.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
