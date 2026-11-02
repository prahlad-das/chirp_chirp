package com.edgesoft.chirp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chirp {
    @Id
    private long id; //change to snowflake id
    private UUID authorId;
    private Instant createdAt;
    private String content;
}
