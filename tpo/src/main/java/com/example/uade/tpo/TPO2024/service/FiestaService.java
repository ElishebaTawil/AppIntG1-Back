package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.FiestaNotFoundException;

public interface FiestaService {

    public List<Fiesta> getFiestas();

    public Optional<Fiesta> getFiestaById(Long fiestaId);

    public List<Fiesta> getFiestasPorPrecio(int montoMaximo);

    public List<Fiesta> getFiestasOrdenadasPorPrecioDeMenorAMayor();

    public List<Fiesta> getFiestasOrdenadasPorPrecioDeMayorAMenor();

    public Fiesta createFiesta(String name, String fecha, String hora, String lugar, String ubicacion, String image,
            int price,
            int cantEntradas,
            boolean available)
            throws FiestaDuplicateException;

    public Fiesta updateFiesta(Long fiestaId, Fiesta fiestaActualizada) throws FiestaNotFoundException;

    public void removeFiesta(Long fiestaId) throws FiestaNotFoundException;
}
