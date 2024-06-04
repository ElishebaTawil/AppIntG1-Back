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

    public Fiesta createFiesta(String name, String fecha, String ubicacion, String image, double price,
            int cantEntradas,
            boolean available)
            throws FiestaDuplicateException {
        List<Fiesta> fiestas = fiestaRepository.findByName(name);
        if (fiestas.isEmpty()) {
            return fiestaRepository.save(new Fiesta(name, fecha, ubicacion, image, price, cantEntradas, available));
        }
        throw new FiestaDuplicateException();
    }

    public Fiesta updateFiesta(Long fiestaId, Fiesta fiestaActualizada) throws FiestaNotFoundException {
        Optional<Fiesta> fiestaOptional = fiestaRepository.findById(fiestaId);
        if (fiestaOptional.isPresent()) {
            Fiesta fiestaPorActualizar = fiestaOptional.get(); // convierto de Optional a User
            fiestaPorActualizar.setName(fiestaActualizada.getName());
            fiestaPorActualizar.setFecha(fiestaActualizada.getFecha());
            fiestaPorActualizar.setUbicacion(fiestaActualizada.getUbicacion());
            fiestaPorActualizar.setImage(fiestaActualizada.getImage());
            fiestaPorActualizar.setPrice(fiestaActualizada.getPrice());
            fiestaPorActualizar.setAvailable(fiestaActualizada.isAvailable());
            return fiestaRepository.save(fiestaActualizada);
        }
        throw new FiestaNotFoundException();
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
