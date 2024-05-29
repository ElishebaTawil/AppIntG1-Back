package com.example.uade.tpo.TPO2024.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
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
}