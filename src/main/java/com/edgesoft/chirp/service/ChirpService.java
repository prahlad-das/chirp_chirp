package com.edgesoft.chirp.service;

import com.edgesoft.chirp.dto.ChirpRequest;
import com.edgesoft.chirp.entity.Chirp;
import com.edgesoft.chirp.repository.ChirpRepository;
import com.edgesoft.chirp.utils.SnowflakeIdGenerator;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ChirpService {
    @Autowired
    ChirpRepository chirpRepository;

    @Autowired
    SnowflakeIdGenerator idGenerator;

    public @Nullable Chirp postChirp(ChirpRequest chirpRequest) {
        Chirp chirp = Chirp.builder()
                .id(idGenerator.nextId())
                .authorId(chirpRequest.getAuthorId())
                .createdAt(Instant.now())
                .content(chirpRequest.getContent())
                .build();
        chirpRepository.save(chirp);
        return chirp;
    }

    public @Nullable Chirp getChirp(long id) {
        Optional<Chirp> chirp = chirpRepository.findById(id);
        return chirp.get();
    }

    public void deleteChirp(long id) {
        chirpRepository.deleteById(id);
    }

    public @Nullable Chirp patchChirp(long id, ChirpRequest chirpRequest) {
        Chirp chirp = this.getChirp(id);
        chirp.setContent(chirpRequest.getContent());
        chirpRepository.save(chirp);
        return chirp;
    }
}