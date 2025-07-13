package org.example.urlshortner.mapper;

import org.example.urlshortner.dto.RegisterUserRequest;
import org.example.urlshortner.dto.UserDto;
import org.example.urlshortner.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    @Mapping(target = "id",ignore = true)
    User toEntity(RegisterUserRequest request);
}
