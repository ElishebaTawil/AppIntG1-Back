package com.example.uade.tpo.TPO2024.service;

import java.util.List;

import com.example.uade.tpo.TPO2024.entity.FiestaAsociada;

public interface FiestaAsociadaService {

    public List<FiestaAsociada> getFiestas();

    public Optional<FiestaAsociada> getFiestaAsociadaById(Long fiestaId);

    public FiestaAsociada createFiesta();

    public void removeFiestaAsociada(Long fiestaId) throws FiestaNotFoundException;
}
