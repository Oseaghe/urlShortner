package org.example.urlshortner.controllers;


import lombok.AllArgsConstructor;
import org.example.urlshortner.dto.UrlRequest;
import org.example.urlshortner.dto.UrlResponse;
import org.example.urlshortner.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@AllArgsConstructor
@RestController
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request){
        UrlResponse response = urlService.shortenUrl(request);
        return ResponseEntity.created(URI.create(response.getShortUrl())).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){
        return urlService.getOriginalUrl(shortCode)
                .map(url -> ResponseEntity.status(302).location(URI.create(url)).build())
                .orElse(ResponseEntity.notFound().build());
    }
}
