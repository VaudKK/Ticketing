package com.sda.ticketing.services;

import com.sda.ticketing.Dto.BookingDto;
import com.sda.ticketing.models.Booking;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {

    Mono<Booking> getBookingById(String bookingId);

    Mono<Booking> getBookingBySeatIdAndSessionId(String seatId,String sessionId,Boolean active);

    Mono<Booking> cancelBooking(String bookingId);

    Mono<Booking> bookSeat(BookingDto bookingDto);

    Mono<Booking> getBookingByTicketNo(String ticketNo);

    Flux<Booking> getBookings(String userId,Boolean active);

    Flux<Booking> getBookings(String userId);

    Flux<Booking> getBookingBySession(String sessionId);

}
