package com.example.concert.service;

import com.example.concert.model.ConcertBookingEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingPublisherService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BookingPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishBooking(ConcertBookingEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("concert-events", event.getCorrelationId(), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}