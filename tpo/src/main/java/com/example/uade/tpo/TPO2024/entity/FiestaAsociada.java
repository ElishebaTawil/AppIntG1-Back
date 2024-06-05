package com.example.uade.tpo.TPO2024.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@Entity
@Table(name = "fiestas_asociadas")
@Getter
@Setter
public class FiestaAsociada {

    public FiestaAsociada() {

    }

    public FiestaAsociada(Fiesta fiesta, OrdenDeCompra orden, int cantidadEntradas, int precio) {
        this.fiesta = fiesta;
        this.orden = orden;
        this.cantidadEntradas = cantidadEntradas;
        this.precio = precio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne
    // @JoinColumn(name = "fiesta_id", nullable = false)
    // private Fiesta fiesta;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenDeCompra orden;

    @Column
    private int cantidadEntradas;

    @Column
    private int precio;
}
