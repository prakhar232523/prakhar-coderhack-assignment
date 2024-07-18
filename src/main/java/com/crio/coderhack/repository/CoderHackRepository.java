package com.crio.coderhack.repository;

import com.crio.coderhack.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoderHackRepository extends MongoRepository<User, String> {
}
