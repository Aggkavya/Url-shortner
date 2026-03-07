package com.urlShortner.Url_Shortner.services;

import com.urlShortner.Url_Shortner.DTO.ShortUrlStatsResponse;
import com.urlShortner.Url_Shortner.entity.ShortUrl;
import com.urlShortner.Url_Shortner.entity.User;
import com.urlShortner.Url_Shortner.repository.ShortUrlRepository;
import com.urlShortner.Url_Shortner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

@Service
public class UrlShortnerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    @Autowired
    private UserService userService;

    public String getByShortId(String shortCode) {
        ShortUrl byShortId = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("short url not found"));
        byShortId.setClicks(byShortId.getClicks() + 1);

        shortUrlRepository.save(byShortId);
        return byShortId.getMainUrl();

    }

    private String generateShortCode() {
        return NanoIdUtils.randomNanoId(
                NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
                NanoIdUtils.DEFAULT_ALPHABET,
                8 // length of code
        ); // default ~21 chars
    }

    public String createNewShortId(String url) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUserName(name)
                .orElseThrow(() -> new UsernameNotFoundException("username note found" + name));

        String newShortId = generateShortCode();
        ShortUrl newShortUrl = ShortUrl.builder()
                .mainUrl(url)
                .shortCode(newShortId)
                .build();
        ShortUrl save = shortUrlRepository.save(newShortUrl);
        user.getUserShortUrls().add(save);
        userService.saveUser(user);
        return newShortUrl.getShortCode();
    }

    public ShortUrlStatsResponse getStats(String shortId) {
        ShortUrl byShortCode = shortUrlRepository.findByShortCode(shortId)
                .orElseThrow(() -> new RuntimeException("no such url found"));
        ShortUrlStatsResponse dto = new ShortUrlStatsResponse();
        dto.setClicks(byShortCode.getClicks());
        dto.setMainUrl(byShortCode.getMainUrl());
        dto.setUpdatedAt(byShortCode.getUpdatedAt());
        dto.setShortCode(byShortCode.getShortCode());
        dto.setCreatedAt(byShortCode.getCreatedAt());
        return dto;
    }

}
