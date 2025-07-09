package org.example.urlshortner.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "url")
public class UrlShort {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "url_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "original_url")
    private String originalUrl;

    @Size(max = 255)
    @NotNull
    @Column(name = "short_code", nullable = false, unique = true)
    private String shortCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

}