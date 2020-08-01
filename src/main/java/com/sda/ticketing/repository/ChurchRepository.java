package com.sda.ticketing.repository;

import com.sda.ticketing.models.Church;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChurchRepository extends ReactiveMongoRepository<Church,String> {
}
