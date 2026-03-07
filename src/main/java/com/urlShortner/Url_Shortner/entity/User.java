package com.urlShortner.Url_Shortner.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    private List<ShortUrl> userShortUrls = new ArrayList<>();

    @Builder.Default
    private Set<String> roles = new HashSet<>();
}
