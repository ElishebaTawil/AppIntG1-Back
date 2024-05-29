package com.example.uade.tpo.TPO2024.service;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;
import com.example.uade.tpo.TPO2024.repository.FiestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiestaServiceImpl implements FiestaService {

    @Autowired
    private FiestaRepository fiestaRepository;

    public List<Fiesta> getAllFiestas() {
        return fiestaRepository.findAll();
    }

    public Fiesta createFiesta(String name, String image, double newPrice, boolean available)
            throws FiestaDuplicateException {
        List<Fiesta> users = fiestaRepository.findAll();
        if (users.stream().anyMatch(user -> user.getName().equals(name)))
            throw new FiestaDuplicateException();
        return fiestaRepository.save(new Fiesta(name, image, newPrice, available));
    }

    public void removeFiesta(Long id) {
        fiestaRepository.deleteById(id);
    }

}
