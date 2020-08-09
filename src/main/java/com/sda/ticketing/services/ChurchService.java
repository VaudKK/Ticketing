package com.sda.ticketing.services;

import com.sda.ticketing.models.Church;
import reactor.core.publisher.Mono;

public interface ChurchService {

    Mono<Church> saveChurch(Church church);

    Mono<Church> getChurchById(String churchId);

}
