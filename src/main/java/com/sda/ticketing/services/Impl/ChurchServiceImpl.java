package com.sda.ticketing.services.Impl;

import com.sda.ticketing.models.Church;
import com.sda.ticketing.repository.ChurchRepository;
import com.sda.ticketing.services.ChurchService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("churchService")
public class ChurchServiceImpl implements ChurchService {

    private final ChurchRepository churchRepository;

    public ChurchServiceImpl(ChurchRepository churchRepository) {
        this.churchRepository = churchRepository;
    }

    @Override
    public Mono<Church> saveChurch(Church church) {
        return churchRepository.save(church);
    }

    @Override
    public Mono<Church> getChurchById(String churchId) {
        return churchRepository.findById(churchId);
    }
}
