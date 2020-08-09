package com.sda.ticketing.repository;

import com.sda.ticketing.models.Seat;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SeatRepository extends ReactiveMongoRepository<Seat,String> {

    Mono<Long> countByChurchId(String churchId);

}
