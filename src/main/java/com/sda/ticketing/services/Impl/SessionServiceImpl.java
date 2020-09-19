package com.sda.ticketing.services.Impl;

import com.sda.ticketing.Dto.SessionDto;
import com.sda.ticketing.exceptions.ErrorHandler;
import com.sda.ticketing.exceptions.SessionException;
import com.sda.ticketing.models.Church;
import com.sda.ticketing.models.Session;
import com.sda.ticketing.repository.ChurchRepository;
import com.sda.ticketing.repository.SessionRepository;
import com.sda.ticketing.services.ChurchService;
import com.sda.ticketing.services.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;
    private final ChurchRepository churchRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, ModelMapper modelMapper, ChurchRepository churchRepository) {
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
        this.churchRepository = churchRepository;
    }

    @Override
    public Mono<Session> createSession(SessionDto sessionDto) {
        Session session = modelMapper.map(sessionDto,Session.class);
        return churchRepository.findById(sessionDto.getChurchId())
                .map(Church::getSeatCount)
                .doOnNext(availableSeats -> {
                    if(sessionDto.getSeatsAvailable()>availableSeats){
                        throw new SessionException("The seats allocated to this session are more than those available");
                    }
                })
                .flatMap(integer -> sessionRepository.save(session))
                .doOnError(ErrorHandler::handleError);
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
    public Flux<Session> getActiveSessions(Pageable pageable) {
        return sessionRepository.findByActive(true,pageable);
    }

    @Override
    public Flux<Session> getActiveSessions(String churchId, Pageable pageable) {
        return sessionRepository.findByChurchIdAndActive(churchId,true,pageable);
    }
}
