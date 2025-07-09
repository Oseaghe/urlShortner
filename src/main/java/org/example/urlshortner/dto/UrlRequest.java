package org.example.urlshortner.dto;

import lombok.Data;

@Data
public class UrlRequest {

    private String originalUrl;
    private String email;
}
