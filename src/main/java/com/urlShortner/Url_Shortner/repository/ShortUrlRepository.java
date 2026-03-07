package com.urlShortner.Url_Shortner.repository;

import com.urlShortner.Url_Shortner.entity.ShortUrl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortCode(String shortCode);
}
