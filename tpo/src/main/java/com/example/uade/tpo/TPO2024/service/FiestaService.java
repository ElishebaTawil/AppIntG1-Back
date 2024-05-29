package com.example.uade.tpo.TPO2024.service;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.repository.FiestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiestaService {

    @Autowired
    private FiestaRepository fiestaRepository;

    public List<Fiesta> getAllFiestas() {
        return fiestaRepository.findAll();
    }

    public Fiesta addFiesta(Fiesta fiesta) {
        return fiestaRepository.save(fiesta);
    }

    public void removeFiesta(String id) {
        fiestaRepository.deleteById(id);
    }
}
