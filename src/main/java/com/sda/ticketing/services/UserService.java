package com.sda.ticketing.services;

import com.sda.ticketing.models.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> saveUser(User user);

    Mono<User> getUser(String phoneNumber);

    Mono<User> getByUserId(String userId);

    Mono<User> updateUser(User user);

    @Deprecated
    Mono<User> directSave(User user);

}
