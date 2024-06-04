package com.example.uade.tpo.TPO2024.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;
import com.example.uade.tpo.TPO2024.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers()
                .stream()
                .filter(user -> "user".equals(user.getRole()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAdmins() {
        List<User> users = userService.getAllUsers()
                .stream()
                .filter(user -> "admin".equals(user.getRole()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new UserNotFoundException();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User userRequest)
            throws UserDuplicateException {
        User result = userService.createUser(userRequest.getName(),
                userRequest.getEmail(), userRequest.getPassword(), userRequest.getRole());
        return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userActualizado)
            throws UserNotFoundException {
        try {
            User usuarioActualizado = userService.updateUser(userId, userActualizado);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El id del usuario no existe", e);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable Long userId) {
        try {
            userService.removeUser(userId);
            return ResponseEntity.ok("Usuario eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: el usuario no existe.");
        }
    }

}
