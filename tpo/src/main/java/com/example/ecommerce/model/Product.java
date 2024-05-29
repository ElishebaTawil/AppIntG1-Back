package com.example.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String image;
    private String category;
    private double newPrice;
    private double oldPrice;
    private boolean available;
}