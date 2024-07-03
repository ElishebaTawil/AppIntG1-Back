package com.example.uade.tpo.TPO2024.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.FiestaNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.OrdenDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.OrdenNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.uade.tpo.TPO2024.dto.FiestaDTORequest;
import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.FiestaAsociada;
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.repository.FiestaRepository;
import com.example.uade.tpo.TPO2024.repository.FiestaRequestRepository;
import com.example.uade.tpo.TPO2024.repository.OrdenDeCompraRepository;
import com.example.uade.tpo.TPO2024.repository.UserRepository;

@Service
public class OrdenDeCompraServiceImpl implements OrdenDeCompraService {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FiestaRepository fiestaRepository;

    public List<OrdenDeCompra> getOrdenes() {
        return ordenDeCompraRepository.findAll();
    }

    public Optional<OrdenDeCompra> getOrdenById(Long ordenId) {
        return ordenDeCompraRepository.findById(ordenId);
    }

    @Transactional
    public OrdenDeCompra createOrden(String email, List<FiestaDTORequest> fiestasRequest, double descuento)
            throws OrdenDuplicateException, UserNotFoundException, FiestaNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException();
        else {
            User user = optionalUser.get();
            String username = user.getName();
            double montoParcial = 0;
            double montoTotal = 0;

            List<FiestaAsociada> fiestasAsociadas = new ArrayList<FiestaAsociada>();

            for (FiestaDTORequest fiestaRequest : fiestasRequest) {

                String fiestaDtoName = fiestaRequest.getName();
                Optional<Fiesta> optionalFiesta = fiestaRepository.findByName(fiestaDtoName);
                if (optionalFiesta.isEmpty())
                    throw new FiestaNotFoundException();
                else {
                    Fiesta fiesta = optionalFiesta.get();
                    montoParcial = montoParcial + fiesta.getPrice() * fiestaRequest.getCantidadEntradas();
                    fiesta.setCantEntradas(fiesta.getCantEntradas() - fiestaRequest.getCantidadEntradas());
                    fiestaRepository.save(fiesta); // acutalizo stock de entradas

                    FiestaAsociada fiestaAsociada = new FiestaAsociada();
                    fiestaAsociada.setName(fiesta.getName());
                    fiestaAsociada.setCantidadEntradas(fiestaRequest.getCantidadEntradas());
                    fiestaAsociada.setMontoParcial(fiesta.getPrice() * fiestaRequest.getCantidadEntradas());
                    fiestasAsociadas.add(fiestaAsociada);

                    System.out.println("EL MONTO PARCIAL ES: " + montoParcial);

                }
            }
            montoTotal = montoParcial - descuento;

            OrdenDeCompra ordenDeCompra = new OrdenDeCompra(null, user, email, username, fiestasAsociadas, montoParcial,
                    descuento, montoTotal);

            // Establecer la relación bidireccional
            for (FiestaAsociada fiesta : fiestasAsociadas) {
                fiesta.setOrdenDeCompra(ordenDeCompra);
            }

            return ordenDeCompraRepository.save(ordenDeCompra);
        }
    }

    public void removeOrden(Long ordenId) throws OrdenNotFoundException {
        Optional<OrdenDeCompra> orden = ordenDeCompraRepository.findById(ordenId);
        if (orden.isPresent()) {
            ordenDeCompraRepository.deleteById(ordenId);
        } else {
            throw new OrdenNotFoundException();
        }
    }

}