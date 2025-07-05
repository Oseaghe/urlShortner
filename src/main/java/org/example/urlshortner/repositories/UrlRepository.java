package org.example.urlshortner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.example.urlshortner.entities.UrlShort;

public interface UrlRepository extends JpaRepository <UrlShort, Integer> {

    Optional<UrlShort> findByShortCode(String shortCode);

    Optional<UrlShort> findByOriginalUrl(String originalUrl);

}
