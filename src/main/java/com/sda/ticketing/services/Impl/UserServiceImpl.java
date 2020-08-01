package com.sda.ticketing.services.Impl;

import com.sda.ticketing.models.User;
import com.sda.ticketing.repository.UserRepository;
import com.sda.ticketing.services.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }
}
