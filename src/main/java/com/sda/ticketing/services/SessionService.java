package com.sda.ticketing.services;

import com.sda.ticketing.Dto.SessionDto;
import com.sda.ticketing.models.Session;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SessionService {

    Mono<Session> createSession(SessionDto sessionDto);

    Mono<Session> closeSession(String sessionId);

    Flux<Session> getActiveSessions();

}
