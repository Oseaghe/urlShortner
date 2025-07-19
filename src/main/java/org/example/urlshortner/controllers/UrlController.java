package org.example.urlshortner.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.urlshortner.dto.UrlRequest;
import org.example.urlshortner.dto.UrlResponse;
import org.example.urlshortner.entities.UrlShort;
import org.example.urlshortner.repositories.UrlRepository;
import org.example.urlshortner.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UrlController {
    private final UrlService urlService;
    private final UrlRepository urlRepository;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody UrlRequest request, Principal principal){
        System.out.println("shortenUrl() CALLED");
        String username = principal.getName();
        UrlResponse response = urlService.shortenUrl(request, username);
        System.out.printf("Generated short code: %s%n", response.getShortUrl());
        return ResponseEntity.created(URI.create(response.getShortUrl())).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){

        return urlService.getOriginalUrl(shortCode)
                .map(url ->{ System.out.printf("Redirecting to: %s%n", url);
                    return ResponseEntity.status(302).location(URI.create(url)).build();})
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/my-urls")
    public ResponseEntity<?> getUserUrls(Principal principal) {
        String email = principal.getName();
        List<UrlShort> urls = urlService.getUrlsByUserEmail(email);
        return ResponseEntity.ok(urls);
    }


}
