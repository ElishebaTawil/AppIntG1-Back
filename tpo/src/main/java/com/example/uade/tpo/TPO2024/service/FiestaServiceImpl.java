package com.example.uade.tpo.TPO2024.service;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.FiestaNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;
import com.example.uade.tpo.TPO2024.repository.FiestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiestaServiceImpl implements FiestaService {

    @Autowired
    private FiestaRepository fiestaRepository;

    public List<Fiesta> getFiestas() {
        return fiestaRepository.findAll();
    }

    public Optional<Fiesta> getFiestaById(Long fiestaId) {
        return fiestaRepository.findById(fiestaId);
    }

    public Fiesta createFiesta(String name, String image, double newPrice, boolean available)
            throws FiestaDuplicateException {
        List<Fiesta> fiestas = fiestaRepository.findByName(name);
        if (fiestas.isEmpty()) {
            return fiestaRepository.save(new Fiesta(name, image, newPrice, available));
        }
        throw new FiestaDuplicateException();
    }

    public void updateFiesta() {

    }

    public void removeFiesta(Long fiestaId) throws FiestaNotFoundException {
        Optional<Fiesta> fiesta = fiestaRepository.findById(fiestaId);
        if (fiesta.isPresent()) {
            fiestaRepository.deleteById(fiestaId);
        } else {
            throw new FiestaNotFoundException();
        }
    }

}
