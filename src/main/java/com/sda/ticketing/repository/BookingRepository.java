package com.sda.ticketing.repository;

import com.sda.ticketing.models.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookingRepository extends ReactiveMongoRepository<Booking,String> {

    Mono<Booking> findBySeatIdAndSessionIdAndActive(String seatId,String sessionId,Boolean active);

    Flux<Booking> findByUserIdAndActive(String userId,Boolean active);

    Flux<Booking> findByUserId(String userId);

    Flux<Booking> findBySessionId(String sessionId);

    Mono<Booking> findByTicketNo(String ticketNo);

    Mono<Long> countBySeatIdAndSessionIdAndActive(String seatId,String sessionId,Boolean active);

}
