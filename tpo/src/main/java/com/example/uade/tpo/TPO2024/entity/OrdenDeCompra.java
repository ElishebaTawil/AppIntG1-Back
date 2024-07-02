package com.example.uade.tpo.TPO2024.entity;

import java.util.List;

import com.example.uade.tpo.TPO2024.dto.FiestaDTO;
import com.example.uade.tpo.TPO2024.dto.FiestaDTORequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordenes")

public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // Ignora la serialización de User en JSON
    private User user;

    @Column
    private String email;

    @Column
    private String username;

    @OneToMany(mappedBy = "ordenDeCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FiestaDTORequest> fiestas;

    @Column
    private double montoParcial;

    @Column
    private double descuento;

    @Column
    private double montoTotal;

}
