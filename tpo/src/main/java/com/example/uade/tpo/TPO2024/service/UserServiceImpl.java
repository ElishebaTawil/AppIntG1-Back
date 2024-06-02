package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User createUser(String name, String email, String password) throws UserDuplicateException {
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            return userRepository.save(new User(name, email, password));
        }
        throw new UserDuplicateException();

    }

    public void updateUser() {

    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

}
