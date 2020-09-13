package com.sda.ticketing.controller;

import com.sda.ticketing.models.Church;
import com.sda.ticketing.services.ChurchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/church")
public class ChurchController {

    private final ChurchService churchService;

    public ChurchController(ChurchService churchService) {
        this.churchService = churchService;
    }

    @PostMapping
    public ResponseEntity<Mono<Church>> createChurch(@RequestBody Church church){
        return ResponseEntity.ok(churchService.saveChurch(church));
    }

    @GetMapping("/{churchId}")
    public ResponseEntity<Mono<Church>> getChurch(@PathVariable("churchId") String churchId){
        return ResponseEntity.ok(churchService.getChurchById(churchId));
    }
}
