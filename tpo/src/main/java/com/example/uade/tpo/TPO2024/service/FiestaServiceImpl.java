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
import java.util.stream.Collectors;

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

    public List<Fiesta> getFiestasPorPrecio(int montoMaximo) {
        List<Fiesta> fiestas = fiestaRepository.findAll();

        // Filtrar las fiestas que tienen un precio menor o igual al monto máximo
        List<Fiesta> fiestasFiltradas = fiestas.stream()
                .filter(f -> f.getPrice() <= montoMaximo)
                .collect(Collectors.toList());

        return fiestasFiltradas;
    }

    public List<Fiesta> getFiestasOrdenadasPorPrecioDeMenorAMayor() {
        List<Fiesta> fiestas = fiestaRepository.findAll();
        return fiestas.stream()
                .sorted((f1, f2) -> Double.compare(f1.getPrice(), f2.getPrice()))
                .collect(Collectors.toList());
    }

    public List<Fiesta> getFiestasOrdenadasPorPrecioDeMayorAMenor() {
        List<Fiesta> fiestas = fiestaRepository.findAll();
        return fiestas.stream()
                .sorted((f1, f2) -> Double.compare(f2.getPrice(), f1.getPrice()))
                .collect(Collectors.toList());
    }

    public Fiesta createFiesta(String name, String fecha, String ubicacion, String image, int price,
            int cantEntradas,
            boolean available)
            throws FiestaDuplicateException {
        Optional<Fiesta> fiesta = fiestaRepository.findByName(name);
        if (fiesta.isEmpty()) {
            return fiestaRepository
                    .save(new Fiesta(null, name, fecha, ubicacion, image, price, cantEntradas, available));
        }
        throw new FiestaDuplicateException();
    }

    public Fiesta updateFiesta(Long fiestaId, Fiesta fiestaActualizada) throws FiestaNotFoundException {
        Optional<Fiesta> fiestaOptional = fiestaRepository.findById(fiestaId);
        if (fiestaOptional.isPresent()) {
            Fiesta fiestaPorActualizar = fiestaOptional.get(); // convierto de Optional a Fiesta
            fiestaPorActualizar.setName(fiestaActualizada.getName());
            fiestaPorActualizar.setFecha(fiestaActualizada.getFecha());
            fiestaPorActualizar.setUbicacion(fiestaActualizada.getUbicacion());
            fiestaPorActualizar.setImage(fiestaActualizada.getImage());
            fiestaPorActualizar.setPrice(fiestaActualizada.getPrice());
            fiestaPorActualizar.setAvailable(fiestaActualizada.isAvailable());
            fiestaPorActualizar.setCantEntradas(fiestaActualizada.getCantEntradas());
            return fiestaRepository.save(fiestaPorActualizar);
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
