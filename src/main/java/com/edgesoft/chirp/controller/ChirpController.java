package com.edgesoft.chirp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/chirps")
@RestController
public class ChirpController {

    //get
    @GetMapping
    public ResponseEntity<String> getChirp(){
        return ResponseEntity.ok("Hello");
    }

    //post
    @PostMapping
    public ResponseEntity<String> postChirp(){
        return ResponseEntity.ok("Hello");
    }

    //patch
    @PatchMapping
    public ResponseEntity<String> updateChirp(){
        return ResponseEntity.ok("Hello");
    }

    //delete
    @DeleteMapping
    public ResponseEntity<String> deleteChirp(){
        return ResponseEntity.ok("Hello");
    }
}
