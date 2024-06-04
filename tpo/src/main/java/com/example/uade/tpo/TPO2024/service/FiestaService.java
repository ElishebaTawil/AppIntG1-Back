package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.FiestaNotFoundException;

public interface FiestaService {

    public List<Fiesta> getFiestas();

    public Optional<Fiesta> getFiestaById(Long fiestaId);

    public Fiesta createFiesta(String name, String fecha, String ubicacion, String image, double price,
            int cantEntradas,
            boolean available)
            throws FiestaDuplicateException;

    public Fiesta updateFiesta(Long fiestaId, Fiesta fiestaActualizada) throws FiestaNotFoundException;

    public void removeFiesta(Long fiestaId) throws FiestaNotFoundException;
}
