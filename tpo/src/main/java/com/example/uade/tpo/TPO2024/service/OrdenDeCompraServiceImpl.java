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
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.repository.FiestaRepository;

import com.example.uade.tpo.TPO2024.repository.OrdenDeCompraRepository;
import com.example.uade.tpo.TPO2024.repository.UserRepository;

@Service
@Transactional
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

            List<FiestaDTORequest> fiestasOrden = new ArrayList<FiestaDTORequest>();

            // OrdenDeCompra ordenDeCompra = new OrdenDeCompra(null, user, email, username,
            // new ArrayList<FiestaDTORequest>(), montoParcial, descuento, montoTotal);

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

                    // Crear una nueva instancia de FiestaDTORequest y asociarla con la orden de
                    // compra
                    // FiestaDTORequest newFiestaRequest = new FiestaDTORequest();
                    // newFiestaRequest.setOrdenDeCompra(ordenDeCompra);
                    // newFiestaRequest.setName(fiestaRequest.getName());
                    // newFiestaRequest.setCantidadEntradas(fiestaRequest.getCantidadEntradas());
                    // newFiestaRequest.setMontoParcial(fiesta.getPrice() *
                    // fiestaRequest.getCantidadEntradas());
                    // ordenDeCompra.getFiestas().add(newFiestaRequest);
                }
            }
            montoTotal = montoParcial - descuento;

            // return ordenDeCompraRepository.save(ordenDeCompra);
            return ordenDeCompraRepository.save(
                    new OrdenDeCompra(null, user, email, username, fiestasOrden, montoParcial, descuento, montoTotal));
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