package com.example.seat.producer;

import com.example.seat.model.SeatReservedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SeatEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SeatEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSeatReserved(SeatReservedEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("seat-events", event.getCorrelationId(), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}