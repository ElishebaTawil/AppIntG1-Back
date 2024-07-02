package com.example.uade.tpo.TPO2024.dto;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
public class FiestaDTO {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne
    // @JoinColumn(name = "orden_id", nullable = false)
    private OrdenDeCompra ordenDeCompra;

    // @OneToOne
    // @JoinColumn(name = "fiesta_id", referencedColumnName = "id")
    // private Fiesta fiesta;

    // @Column
    private String name;

    // @Column
    private int cantidadEntradas;

    // @Column
    private double montoParcial; // PRECIO * CANTIDAD

}
