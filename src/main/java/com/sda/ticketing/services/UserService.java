package com.sda.ticketing.services;

import com.sda.ticketing.models.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> getUserByPhoneNumber(String phoneNumber);

    Mono<User> saveUser(User user);

}
