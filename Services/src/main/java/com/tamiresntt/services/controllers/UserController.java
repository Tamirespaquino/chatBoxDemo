package com.tamiresntt.services.controllers;

import com.tamiresntt.services.models.User;
import com.tamiresntt.services.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/users")
    public Mono<User> saveUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/user/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String id) {
        return userRepo.findById(id).map(savedUser -> ResponseEntity.ok(savedUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
