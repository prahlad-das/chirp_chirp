package com.edgesoft.chirp.controller;

import com.edgesoft.chirp.dto.ChirpRequest;
import com.edgesoft.chirp.entity.Chirp;
import com.edgesoft.chirp.service.ChirpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/chirps")
@RestController
public class ChirpController {

    @Autowired
    ChirpService chirpService;

    //get
    @GetMapping()
    public ResponseEntity<Chirp> getChirp(@RequestParam("id") long id){
        Chirp chirp = chirpService.getChirp(id);
        return ResponseEntity.ok(chirp);
    }

    //post
    @PostMapping
    public ResponseEntity<Chirp> postChirp(@RequestBody ChirpRequest chirpRequest){
        Chirp chirp = chirpService.postChirp(chirpRequest);
        return ResponseEntity.ok(chirp);
    }

    //patch
    @PatchMapping
    public ResponseEntity<Chirp> updateChirp(@RequestParam("id") long id, @RequestBody ChirpRequest chirpRequest){
        Chirp chirp = chirpService.patchChirp(id, chirpRequest);
        return ResponseEntity.ok(chirp);
    }

    //delete
    @DeleteMapping
    public ResponseEntity<String> deleteChirp(@RequestParam("id") long id){
        chirpService.deleteChirp(id);
        return ResponseEntity.ok("Chirp deleted with id " + id);
    }
}
