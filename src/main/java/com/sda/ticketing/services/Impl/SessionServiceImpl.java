package com.sda.ticketing.services.Impl;

import com.sda.ticketing.Dto.SessionDto;
import com.sda.ticketing.models.Session;
import com.sda.ticketing.repository.SessionRepository;
import com.sda.ticketing.services.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    public SessionServiceImpl(SessionRepository sessionRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<Session> createSession(SessionDto sessionDto) {
        Session session = modelMapper.map(sessionDto,Session.class);
        return sessionRepository.save(session);
    }

    @Override
    public Mono<Session> closeSession(String sessionId) {
        return sessionRepository.findById(sessionId)
                    .flatMap(session -> {
                        session.setActive(false);
                        return  sessionRepository.save(session);
                    });
    }

    @Override
    public Flux<Session> getActiveSessions() {
        return sessionRepository.findByActive(true);
    }
}
