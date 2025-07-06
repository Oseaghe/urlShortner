package org.example.urlshortner.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // enables CORS with your WebMvcConfigurer bean
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/urlusers").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/shorten").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/{shortCode}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/{shortCode}").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}