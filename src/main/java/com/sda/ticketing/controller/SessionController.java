package com.sda.ticketing.controller;

import com.sda.ticketing.Dto.SessionDto;
import com.sda.ticketing.models.Session;
import com.sda.ticketing.services.SessionService;
import com.sda.ticketing.utils.TokenUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;
    private final TokenUtils tokenUtils;

    public SessionController(SessionService sessionService, TokenUtils tokenUtils) {
        this.sessionService = sessionService;
        this.tokenUtils = tokenUtils;
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
    public ResponseEntity<Flux<Session>> getAllActiveSessions(Authentication authentication,@RequestParam("page") int pageIndex,
                                                              @RequestParam("size") int pageSize){
        tokenUtils.initialize(authentication);
        System.out.println(tokenUtils.getPhoneNumber());
        return ResponseEntity.ok(sessionService.getActiveSessions(PageRequest.of(pageIndex,pageSize,Sort.by("createdAt").descending())));
    }

    @GetMapping("/{churchId}")
    public ResponseEntity<Flux<Session>> getActiveSessions(@PathVariable String churchId,
                                                           @RequestParam("page") int pageIndex,
                                                           @RequestParam("size") int pageSize){
        return ResponseEntity.ok(sessionService.getActiveSessions(churchId,PageRequest.of(pageIndex,pageSize,Sort.by("createdAt").descending())));
    }
}
