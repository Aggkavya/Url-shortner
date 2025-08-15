package com.urlShortner.Url_Shortner.repository;

import com.urlShortner.Url_Shortner.entity.ShortUrl;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShortUrlRepositroy extends MongoRepository<ShortUrl , ObjectId> {
   Optional<ShortUrl> findByShortCode(String shortCode);
}
