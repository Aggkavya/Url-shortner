package com.urlShortner.Url_Shortner.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShortUrlStatsResponse
{
    private String shortCode;
    private String mainUrl;

    private Long clicks;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
