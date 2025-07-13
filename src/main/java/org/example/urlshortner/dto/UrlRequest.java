package org.example.urlshortner.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UrlRequest {
    @NotBlank(message = "Original URL is required")
    private String originalUrl;
}
