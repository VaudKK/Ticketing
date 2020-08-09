package com.sda.ticketing.services.Impl;

import com.sda.ticketing.exceptions.ErrorHandler;
import com.sda.ticketing.models.Church;
import com.sda.ticketing.repository.ChurchRepository;
import com.sda.ticketing.services.ChurchService;
import com.sda.ticketing.services.SeatService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("churchService")
public class ChurchServiceImpl implements ChurchService {

    private final ChurchRepository churchRepository;
    private final SeatService seatService;

    public ChurchServiceImpl(ChurchRepository churchRepository, SeatService seatService) {
        this.churchRepository = churchRepository;
        this.seatService = seatService;
    }

    @Override
    public Mono<Church> saveChurch(Church church) {
        return churchRepository.save(church)
                .doOnNext(savedChurch -> {
                    if(savedChurch.getSeatCount() != 0){
                        seatService.addSeats(savedChurch.getSeatCount(),
                                savedChurch.get_id()).subscribe();
                    }
                })
                .doOnError(ErrorHandler::handleError);
    }

    @Override
    public Mono<Church> getChurchById(String churchId) {
        return churchRepository.findById(churchId);
    }
}
