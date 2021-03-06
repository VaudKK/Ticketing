package com.sda.ticketing.repository;

import com.sda.ticketing.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {

    Mono<User> findByPhoneNumber(String phoneNumber);

}
