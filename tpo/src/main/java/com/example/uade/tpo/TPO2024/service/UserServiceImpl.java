package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(String name, String email, String password) throws UserDuplicateException {
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            String passwordHasheada = passwordEncoder.encode(password);
            return userRepository.save(new User(name, email, passwordHasheada));
        }
        throw new UserDuplicateException();

    }

    public boolean checkPassword(String email, String password) {
        List<User> users = userRepository.findByEmail(email);

        if (users.isEmpty()) {
            return false; // Usuario no encontrado
        } else {
            User user = users.get(0);
            return passwordEncoder.matches(password, user.getPassword());
        }
    }

    public void updateUser() {

    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

}
