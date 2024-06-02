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

@Data
@Entity
@Table(name = "fiestas")
public class Fiesta {

    public Fiesta() {

    }

    public Fiesta(String name, String image, double newPrice, boolean available) {
        this.name = name;
        this.image = image;
        this.newPrice = newPrice;
        this.available = available;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private String category;

    @Column
    private double newPrice;

    private double oldPrice;

    @Column
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "ordenDeCompra_id", nullable = false)
    private OrdenDeCompra orden;
}