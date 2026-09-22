package com.example.concert.controller;

import com.example.concert.model.ConcertBookingEvent;
import com.example.concert.service.BookingPublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class ConcertBookingController {
    private final BookingPublisherService publisherService;

    public ConcertBookingController(BookingPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<String> bookTicket(@RequestBody ConcertBookingEvent event) {
        publisherService.publishBooking(event);
        return ResponseEntity.ok("Booking request sent with correlationId: " + event.getCorrelationId());
    }
}