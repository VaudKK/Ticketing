package com.sda.ticketing.repository;

import com.sda.ticketing.models.Session;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SessionRepository extends ReactiveMongoRepository<Session,String> {

    Flux<Session> findByActive(boolean isActive);

}
