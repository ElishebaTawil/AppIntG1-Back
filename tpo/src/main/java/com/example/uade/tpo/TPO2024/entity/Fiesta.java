package com.example.uade.tpo.TPO2024.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fiestas")

public class Fiesta {

    public Fiesta() {

    }

    public Fiesta(String name, String fecha, String ubicacion, String image, int price, int cantEntradas,
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
    private int price;

    @Column
    private int cantEntradas;

    @Column
    private boolean available;

}