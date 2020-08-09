package com.sda.ticketing.services.Impl;

import com.sda.ticketing.exceptions.ErrorHandler;
import com.sda.ticketing.models.Seat;
import com.sda.ticketing.repository.SeatRepository;
import com.sda.ticketing.services.SeatService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service("seatService")
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Flux<Seat> addSeats(int seats, String churchId) {
        List<Seat> addedSeats = new ArrayList<>();
        return seatRepository.countByChurchId(churchId)
                .flatMapMany(integer -> {
                    for (int i = 0; i < seats ; i++) {
                        Seat seat = new Seat();
                        seat.setSeatNumber(integer.intValue() + i+1);
                        seat.setChurchId(churchId);
                        addedSeats.add(seat);
                    }
                    return seatRepository.saveAll(addedSeats);
                })
                .doOnError(ErrorHandler::handleError);
    }

    @Override
    public Flux<Void> removeSeats(int seats, String churchId) {
        return null;
    }
}
