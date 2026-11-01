package com.edgesoft.chirp.repository;

import com.edgesoft.chirp.entity.Chirp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChirpRepository extends MongoRepository<Chirp, String> {
}
