package com.example.uade.tpo.TPO2024.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.TPO2024.auth.RegisterRequest.RegisterRequestBuilder;
import com.example.uade.tpo.TPO2024.auth.AuthenticationRequest;
import com.example.uade.tpo.TPO2024.auth.AuthenticationResponse;
import com.example.uade.tpo.TPO2024.auth.RegisterRequest;
import com.example.uade.tpo.TPO2024.config.JwtService;
import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        @Autowired
        private UserRepository userRepository;

        public AuthenticationResponse register(RegisterRequest request) throws UserDuplicateException {
                var user = User.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(request.getRole())
                                .build();

                Optional<User> userExistente = userRepository.findByEmail(user.getEmail());

                if (userExistente.isPresent()) {
                        throw new UserDuplicateException();
                }

                repository.save(user);

                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder().accessToken(jwtToken).build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                var user = repository.findByEmail(request.getEmail())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}
