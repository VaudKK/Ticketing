package com.sda.ticketing.services;

import com.sda.ticketing.models.Seat;
import reactor.core.publisher.Flux;

public interface SeatService {

    Flux<Seat> createSeats(int seats,String churchId);

    Flux<Seat> addSeats(int seats,String churchId);

    Flux<Void> removeSeats(int seats,String churchId);

}
