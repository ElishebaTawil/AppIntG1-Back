package com.example.uade.tpo.TPO2024.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> result = userService.getUserById(userId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User userRequest)
            throws UserDuplicateException {
        User result = userService.createUser(userRequest.getEmail(),
                userRequest.getName(), userRequest.getPassword());
        return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam String email, @RequestParam String password) {
        boolean loginSuccess = userService.checkPassword(email, password);
        if (loginSuccess) {
            return ResponseEntity.ok("Usuario ya registrado");
        } else {
            return ResponseEntity.status(401).body("Email o Contraseña incorrecta");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable Long userId) {
        Optional<User> result = userService.getUserById(userId);
        if (result.isPresent())
            return ResponseEntity.created(URI.create("/users/" + result.get())).body(result);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removeUser(@RequestBody Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok().build();
    }

}
