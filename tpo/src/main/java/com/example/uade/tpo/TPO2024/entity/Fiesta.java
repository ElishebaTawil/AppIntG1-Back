package com.example.uade.tpo.TPO2024.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "fiestas")
@Getter
@Setter
public class Fiesta {

    public Fiesta() {

    }

    public Fiesta(String name, String fecha, String ubicacion, String image, double price, int cantEntradas,
            boolean available) {
        this.name = name;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.image = image;
        this.price = price;
        this.cantEntradas = cantEntradas;
        this.available = available;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String fecha;

    @Column
    private String ubicacion;

    @Column
    private String image;

    @Column
    private double price;

    @Column
    private int cantEntradas;

    @Column
    private boolean available;

    @ManyToOne(optional = true)
    @JoinColumn(name = "orden_de_compra_id", nullable = true)
    private OrdenDeCompra orden;

    private int cantidadPorFiesta;
}