package com.example.uade.tpo.TPO2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;

@Repository
public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Long> {

}
