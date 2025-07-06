package org.example.urlshortner.controllers;

import lombok.AllArgsConstructor;
import org.example.urlshortner.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class RootRedirectController {

        private final UrlService urlService;

        @GetMapping("/{shortCode}")
        public ResponseEntity<?> redirectFromRoot(@PathVariable String shortCode) {
            return urlService.getOriginalUrl(shortCode)
                    .map(url -> ResponseEntity.status(302).location(URI.create(url)).build())
                    .orElse(ResponseEntity.notFound().build());
        }
    }

