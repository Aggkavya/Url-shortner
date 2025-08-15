package com.urlShortner.Url_Shortner.services;

import com.urlShortner.Url_Shortner.DTO.ShortUrlStatsResponse;
import com.urlShortner.Url_Shortner.entity.ShortUrl;
import com.urlShortner.Url_Shortner.repository.ShortUrlRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlShortnerService {
    @Autowired
    private ShortUrlRepositroy shortUrlRepositroy;

    public String getByShortId(String shortCode){
        ShortUrl byShortId = shortUrlRepositroy.findByShortCode(shortCode).
                orElseThrow(() -> new RuntimeException("short url not found"));
        byShortId.setClicks(byShortId.getClicks()+1);

        shortUrlRepositroy.save(byShortId);
        return byShortId.getMainUrl();

    }


    private String generateShortCode() {
        return NanoIdUtils.randomNanoId(
                NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
                NanoIdUtils.DEFAULT_ALPHABET,
                8 // length of code
        ); // default ~21 chars
    }
    public String createNewShortId(String url){
        String newShortId = generateShortCode();
        ShortUrl newShortUrl = new ShortUrl();
        newShortUrl.setMainUrl(url);
        newShortUrl.setShortCode(newShortId);


        shortUrlRepositroy.save(newShortUrl);
        return newShortUrl.getShortCode();
    }
    public ShortUrlStatsResponse getStats(String shortId){
        ShortUrl byShortCode = shortUrlRepositroy.findByShortCode(shortId).
                orElseThrow(() -> new RuntimeException("no such url found"));
        ShortUrlStatsResponse dto = new ShortUrlStatsResponse();
        dto.setClicks(byShortCode.getClicks());
        dto.setMainUrl(byShortCode.getMainUrl());
        dto.setUpdatedAt(byShortCode.getUpdatedAt());
        dto.setShortCode(byShortCode.getShortCode());
        dto.setCreatedAt(byShortCode.getCreatedAt());
        return dto;
    }

}
