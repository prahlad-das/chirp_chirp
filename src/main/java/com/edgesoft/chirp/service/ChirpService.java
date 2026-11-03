package com.edgesoft.chirp.service;

import com.edgesoft.chirp.dto.ChirpRequest;
import com.edgesoft.chirp.entity.Chirp;
import com.edgesoft.chirp.repository.ChirpRepository;
import com.edgesoft.chirp.utils.SnowflakeIdGenerator;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class ChirpService {
    @Autowired
    ChirpRepository chirpRepository;

    @Autowired
    SnowflakeIdGenerator idGenerator;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Transactional
    public @Nullable Chirp postChirp(ChirpRequest chirpRequest) {
        long id = idGenerator.nextId();
        Chirp chirp = Chirp.builder()
                .id(id)
                .authorId(chirpRequest.getAuthorId())
                .createdAt(Instant.now())
                .content(chirpRequest.getContent())
                .build();
        chirpRepository.save(chirp);
        kafkaProducerService.sendMessage(chirp);
        return chirp;
    }

    @Cacheable(value = "chirps", key = "#id")
    public @Nullable Chirp getChirp(long id) {
        Optional<Chirp> chirp = chirpRepository.findById(id);
        return chirp.get();
    }

    @CacheEvict(value = "chirps", key = "#id")
    public void deleteChirp(long id) {
        chirpRepository.deleteById(id);
    }

    @CacheEvict(value = "chirps", key = "#id")
    public @Nullable Chirp patchChirp(long id, ChirpRequest chirpRequest) {
        Chirp chirp = this.getChirp(id);
        chirp.setContent(chirpRequest.getContent());
        chirpRepository.save(chirp);
        return chirp;
    }
}