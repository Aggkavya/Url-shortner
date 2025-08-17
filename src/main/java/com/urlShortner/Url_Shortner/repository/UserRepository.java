package com.urlShortner.Url_Shortner.repository;

import com.urlShortner.Url_Shortner.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User , ObjectId> {
}
