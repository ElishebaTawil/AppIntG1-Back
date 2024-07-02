package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.TPO2024.entity.Role;
import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;
import com.example.uade.tpo.TPO2024.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        // Filtrar los usuarios que tienen el rol "user"
        List<User> usersFiltrados = users.stream()
                .filter(user -> "user".equals(user.getRole()))
                .collect(Collectors.toList());

        return usersFiltrados;
    }

    public List<User> getAdmins() {
        List<User> users = userRepository.findAll();

        // Filtrar los usuarios que tienen el rol "admin"
        List<User> usersFiltrados = users.stream()
                .filter(user -> "admin".equals(user.getRole()))
                .collect(Collectors.toList());

        return usersFiltrados;
    }

    public Optional<User> getUserById(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    public User createUser(String name, String email, String password, Role role) throws UserDuplicateException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {

            return userRepository.save(new User(null, name, email, password, role, null));
        }
        throw new UserDuplicateException();
    }

    public User updateUser(Long userId, User userActualizado) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User userPorActualizar = userOptional.get(); // convierto de Optional a User
            userPorActualizar.setName(userActualizado.getName());
            userPorActualizar.setEmail(userActualizado.getEmail());
            userPorActualizar.setPassword(userActualizado.getPassword());
            userPorActualizar.setRole(userActualizado.getRole());
            return userRepository.save(userPorActualizar);
        }
        throw new UserNotFoundException();
    }

    public void removeUser(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException();
        }
    }

}
