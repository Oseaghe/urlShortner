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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "originalUrl")
    private String originalUrl;

    @Size(max = 255)
    @NotNull
    @Column(name = "ShortCode", nullable = false)
    private String shortCode;

}