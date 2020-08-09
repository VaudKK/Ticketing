package com.sda.ticketing.controller;

import com.sda.ticketing.Dto.BookingDto;
import com.sda.ticketing.models.Booking;
import com.sda.ticketing.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<Mono<Booking>> bookSeat(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.bookSeat(bookingDto));
    }

    @PostMapping("/cancel")
    public ResponseEntity<Mono<Booking>> cancelBooking(@RequestBody String bookingId){
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }

    @GetMapping
    public ResponseEntity<Flux<Booking>> getBookingByUser(){
        return ResponseEntity.ok(bookingService.getBookings("vaud"));
    }
}
