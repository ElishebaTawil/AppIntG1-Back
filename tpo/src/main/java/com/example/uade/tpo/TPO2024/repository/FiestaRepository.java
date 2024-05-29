package com.example.uade.tpo.TPO2024.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.TPO2024.entity.Fiesta;

@Repository
public interface FiestaRepository extends MongoRepository<Fiesta, String> {
}
