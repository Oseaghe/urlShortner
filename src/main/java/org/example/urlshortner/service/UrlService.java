package org.example.urlshortner.service;


import lombok.AllArgsConstructor;
import org.example.urlshortner.dto.UrlRequest;
import org.example.urlshortner.dto.UrlResponse;
import org.example.urlshortner.entities.UrlShort;
import org.example.urlshortner.entities.User;
import org.example.urlshortner.repositories.UrlRepository;
import org.example.urlshortner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    @Value("${base.url:http://localhost:8080/}")
    private String baseUrl;

    public UrlService(UrlRepository urlRepository, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    public UrlResponse shortenUrl(UrlRequest request, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow();
        String shortCode = generateShortCode();
        UrlShort url = UrlShort.builder()
                .originalUrl(request.getOriginalUrl())
                .shortCode(shortCode)
                .user(user)
                .build();
        urlRepository.save(url);

        return new UrlResponse(baseUrl+ "api/" + shortCode);
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


    public List<UrlShort> getUrlsByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return urlRepository.findAllByUser(user);
    }

}
