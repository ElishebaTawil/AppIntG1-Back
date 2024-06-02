package com.example.uade.tpo.TPO2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.TPO2024.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select c from User c where c.email = ?1 ")
    List<User> findByEmail(String email);

}
