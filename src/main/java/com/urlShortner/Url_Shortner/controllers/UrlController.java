package com.urlShortner.Url_Shortner.controllers;

import com.urlShortner.Url_Shortner.DTO.CreateShortUrlRequest;
import com.urlShortner.Url_Shortner.DTO.ShortUrlStatsResponse;
import com.urlShortner.Url_Shortner.entity.ShortUrl;
import com.urlShortner.Url_Shortner.services.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/short-url")
public class UrlController {

    @Autowired
    private UrlShortnerService urlShortnerService;

    @GetMapping("/{shortId}")
    public ResponseEntity<?> redirectUrl(@PathVariable String shortId){
        String mainUrl = urlShortnerService.getByShortId(shortId);
        return ResponseEntity.status(302)
                .header("Location", mainUrl)
                .build();
    }
    @GetMapping("/check-stats/{shortId}")
    public ResponseEntity<?> getStatsByShortCode(@PathVariable String shortId){
        if(shortId.isBlank()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ShortUrlStatsResponse stats = urlShortnerService.getStats(shortId);
        return new ResponseEntity<>(stats , HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createNewShortId(@RequestBody CreateShortUrlRequest request) {
        if (request.getMainUrl() == null || request.getMainUrl().isBlank()) {
            return ResponseEntity.badRequest().body("URL cannot be empty");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        String shortCode = urlShortnerService.createNewShortId(request.getMainUrl() , name);
        String shortUrl = "http://localhost:3030/short-url/" + shortCode;

        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }


}
