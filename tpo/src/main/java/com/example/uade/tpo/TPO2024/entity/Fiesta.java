package com.example.uade.tpo.TPO2024.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "fiestas")
public class Fiesta {
    @Id
    private String id;
    private String name;
    private String image;
    private String category;
    private double newPrice;
    private double oldPrice;
    private boolean available;
}