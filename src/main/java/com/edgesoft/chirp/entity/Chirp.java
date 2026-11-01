package com.edgesoft.chirp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chirp {
    private String id; //change to snowflake id
    private UUID authorId;
    private Instant createdAt;
    private String content;
}
