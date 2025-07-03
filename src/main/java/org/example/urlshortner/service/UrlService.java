package org.example.urlshortner.service;


import org.example.urlshortner.dto.UrlRequest;
import org.example.urlshortner.dto.UrlResponse;
import org.example.urlshortner.entities.UrlShort;
import org.example.urlshortner.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    @Value("${base.url:http://localhost:8080/}")
    private String baseUrl;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse shortenUrl(UrlRequest request){
        String shortCode = generateShortCode();
        UrlShort url = UrlShort.builder()
                .originalUrl(request.getOriginalUrl())
                .shortCode(shortCode)
                .build();
        urlRepository.save(url);
        return new UrlResponse(baseUrl + shortCode);
    }

    public Optional<String> getOriginalUrl(String code){
        return  urlRepository.findByShortCode(code).map(UrlShort::getOriginalUrl);
    }

    private String generateShortCode(){
        String chars = "abcdefghijklmonpqrstuvwxyzABCDEFGHIJKLMONPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        if(urlRepository.findByShortCode(code.toString()).isPresent()){
            return generateShortCode();
        }
        return code.toString();

    }

}
