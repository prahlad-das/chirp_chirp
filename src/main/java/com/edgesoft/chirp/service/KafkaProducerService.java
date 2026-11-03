package com.edgesoft.chirp.service;

import com.edgesoft.chirp.entity.Chirp;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "chirp-topic";
    private final KafkaTemplate<String, Chirp> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Chirp> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Chirp chirp) {
        kafkaTemplate.send(TOPIC, chirp.getAuthorId().toString(), chirp);
    }
}
