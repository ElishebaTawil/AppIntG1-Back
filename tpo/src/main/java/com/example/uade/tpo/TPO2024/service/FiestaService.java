package com.example.uade.tpo.TPO2024.service;

import java.util.List;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;

public interface FiestaService {

    public List<Fiesta> getAllFiestas();

    public Fiesta createFiesta(String name, String image, double newPrice, boolean available)
            throws FiestaDuplicateException;

    public void removeFiesta(Long id);
}
