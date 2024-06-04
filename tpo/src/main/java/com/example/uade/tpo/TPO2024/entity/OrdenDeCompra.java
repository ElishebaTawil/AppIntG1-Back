package com.example.uade.tpo.TPO2024.entity;

import java.util.List;

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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "ordenes")
@Getter
@Setter
public class OrdenDeCompra {

    public OrdenDeCompra() {

    }

    public OrdenDeCompra(Long id, List<Fiesta> fiestas, int montoTotal) {
        this.id = id;
        this.fiestas = fiestas;
        this.montoTotal = montoTotal;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orden")
    private List<Fiesta> fiestas;

    @Column
    private int montoTotal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
