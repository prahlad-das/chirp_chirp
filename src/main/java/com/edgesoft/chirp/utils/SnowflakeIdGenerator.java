package com.edgesoft.chirp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator {
    private final long MACHINE_BITS = 10L;
    private final long SEQUENCE_BITS = 12L;
    private final long machineId;
    private long sequenceNumber = 0L;
    private long prevTimeStamp = -1L;

    public SnowflakeIdGenerator(@Value("${spring.application.machineId}") String machineId){
//        System.out.println(machineId);
        this.machineId = Long.parseLong(machineId);
    }

    public synchronized long nextId() {
        // Clock skewness is ignored here
        // As this is demo project only
        long epoch = System.currentTimeMillis();
        if (prevTimeStamp == epoch) {
            sequenceNumber += 1;
        }else {
            sequenceNumber = 0L;
            prevTimeStamp = epoch;
        }
        return (((epoch << MACHINE_BITS) + machineId) << SEQUENCE_BITS) + sequenceNumber;

    }
}
