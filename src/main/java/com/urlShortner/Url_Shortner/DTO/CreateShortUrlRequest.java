package com.urlShortner.Url_Shortner.DTO;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
public class CreateShortUrlRequest {
    private String mainUrl;

}
