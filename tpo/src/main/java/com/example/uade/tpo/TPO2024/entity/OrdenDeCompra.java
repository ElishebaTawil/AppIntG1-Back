package com.example.uade.tpo.TPO2024.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class OrdenDeCompra {

    public OrdenDeCompra() {

    }

    public OrdenDeCompra(Long id, Long idUsuario, List<Fiesta> fiestas, int montoTotal) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fiestas = fiestas;
        this.montoTotal = montoTotal;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idUsuario;

    @OneToMany(mappedBy = "orden")
    private List<Fiesta> fiestas;

    @Column
    private int cantidad;

    @Column
    private int montoTotal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
