package com.edgesoft.chirp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ChirpRequest {
    private UUID authorId;
    private String content;
}
