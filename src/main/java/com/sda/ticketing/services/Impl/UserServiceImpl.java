package com.sda.ticketing.services.Impl;

import com.sda.ticketing.models.User;
import com.sda.ticketing.repository.UserRepository;
import com.sda.ticketing.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Mono<User> saveUser(User user) {
        return Mono.just(user)
                .map(newUser -> {
                    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    return newUser;
                })
                .subscribeOn(Schedulers.parallel())
                .flatMap(userRepository::save);
    }

    @Override
    public Mono<User> getUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Mono<User> getByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return userRepository.findById(user.get_id())
                .flatMap(u -> {
                    User updatedUser = modelMapper.map(u,User.class);
                    return userRepository.save(updatedUser);
                });
    }

    @Override
    public Mono<User> directSave(User user) {
        return userRepository.save(user);
    }
}
