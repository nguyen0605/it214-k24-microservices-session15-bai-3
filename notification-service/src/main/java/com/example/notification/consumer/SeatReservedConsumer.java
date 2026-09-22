package com.example.notification.consumer;

import com.example.notification.model.SeatReservedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SeatReservedConsumer {
    private static final Logger log = LoggerFactory.getLogger(SeatReservedConsumer.class);

    @KafkaListener(topics = "seat-events", groupId = "notification-group")
    public void handleSeatReserved(ConsumerRecord<String, String> record) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SeatReservedEvent event = mapper.readValue(record.value(), SeatReservedEvent.class);
            String correlationId = event.getCorrelationId();
            String email = event.getCustomerEmail();

            log.info("[NotifyService] Received confirmation for correlationId: {} - Sending email to {}", correlationId, email);

        } catch (Exception e) {
            log.error("Error processing seat reserved event: {}", e.getMessage());
        }
    }
}