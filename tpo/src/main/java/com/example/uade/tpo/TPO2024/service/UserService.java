package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;

public interface UserService {

    public List<User> getUsers();

    public Optional<User> getUserById(Long userId);

    public User createUser(String name, String email, String password) throws UserDuplicateException;

    public User updateUser(Long id);

    public void removeUser(Long id);
}
