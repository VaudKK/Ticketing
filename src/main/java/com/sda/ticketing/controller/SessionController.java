package com.sda.ticketing.controller;

import com.sda.ticketing.Dto.SessionDto;
import com.sda.ticketing.models.Session;
import com.sda.ticketing.services.SessionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<Mono<Session>> createSession(@RequestBody SessionDto sessionDto){
        return ResponseEntity.ok(sessionService.createSession(sessionDto));
    }

    @PostMapping("/close")
    public ResponseEntity<Mono<Session>> closeSession(@RequestBody String sessionId){
        return ResponseEntity.ok(sessionService.closeSession(sessionId));
    }

    @GetMapping("/all")
    public ResponseEntity<Flux<Session>> getAllActiveSessions(@PageableDefault(size = 50,sort = "createdAt",
                                                            direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(sessionService.getActiveSessions(pageable));
    }

    @GetMapping("/{churchId}")
    public ResponseEntity<Flux<Session>> getActiveSessions(@PathVariable String churchId,
                                                           @PageableDefault(size = 50,sort = "createdAt",
                                                                   direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(sessionService.getActiveSessions(churchId,pageable));
    }
}
