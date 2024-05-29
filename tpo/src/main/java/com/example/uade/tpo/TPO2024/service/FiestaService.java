package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;

public interface FiestaService {

    public List<Fiesta> getFiestas();

    public Optional<Fiesta> getFiestaById(Long fiestaId);

    public Fiesta createFiesta(String name, String image, double newPrice, boolean available)
            throws FiestaDuplicateException;

    public Fiesta updateFiesta(Long id);

    public void removeFiesta(Long id);
}
