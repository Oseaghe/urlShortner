package org.example.urlshortner.mapper;

import org.example.urlshortner.dto.RegisterUserRequest;
import org.example.urlshortner.dto.UserDto;
import org.example.urlshortner.dto.UrlDto;
import org.example.urlshortner.entities.UrlShort;
import org.example.urlshortner.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    // Needed to map UrlShort -> UrlDto (fixes your earlier MapStruct complaint)
    UrlDto toDto(UrlShort urlShort);

    User toEntity(RegisterUserRequest request);
}
