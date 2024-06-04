package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;

public interface UserService {

    public List<User> getAllUsers();

    public List<User> getUsers();

    public List<User> getAdmins();

    public Optional<User> getUserById(Long userId) throws UserNotFoundException;

    public User createUser(String name, String email, String password, String role) throws UserDuplicateException;

    public User updateUser(Long userId, User userActualizado) throws UserNotFoundException;

    public void removeUser(Long userId) throws UserNotFoundException;

}
