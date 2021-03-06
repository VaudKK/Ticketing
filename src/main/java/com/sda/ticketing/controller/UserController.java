package com.sda.ticketing.controller;

import com.sda.ticketing.models.User;
import com.sda.ticketing.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/uaa")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Mono<User>> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

}
