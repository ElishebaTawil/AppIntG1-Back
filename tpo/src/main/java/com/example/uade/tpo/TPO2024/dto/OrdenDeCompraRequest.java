package com.example.uade.tpo.TPO2024.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrdenDeCompraRequest {
    private String email;
    private List<FiestaDTORequest> fiestas;
    private double descuento;
}
