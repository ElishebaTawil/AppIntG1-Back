package com.example.uade.tpo.TPO2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.TPO2024.entity.Fiesta;

@Repository
public interface FiestaRepository extends JpaRepository<Fiesta, Long> {
    @Query(value = "select c from Fiesta c where c.name = ?1 ")
    List<Fiesta> findByName(String name);
}
