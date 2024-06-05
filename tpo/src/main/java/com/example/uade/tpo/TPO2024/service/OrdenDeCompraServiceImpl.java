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

import com.example.uade.tpo.TPO2024.dto.FiestaDTO;
import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.repository.FiestaRepository;
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

    public OrdenDeCompra createOrden(Long userId, List<FiestaDTO> fiestasDTO)
            throws OrdenDuplicateException, UserNotFoundException, FiestaNotFoundException {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException();
        else {
            User user = optionalUser.get();
            int montoTotal = 0;
            for (FiestaDTO fiestaDTO : fiestasDTO) {
                Long fiestaDTOId = fiestaDTO.getFiesta().getId();
                Optional<Fiesta> optionalFiesta = fiestaRepository.findById(fiestaDTOId);
                if (optionalFiesta.isEmpty())
                    throw new FiestaNotFoundException();
                else {
                    Fiesta fiesta = optionalFiesta.get();
                    montoTotal = montoTotal + fiesta.getPrice() * fiestaDTO.getCantidadEntradas();
                    fiesta.setCantEntradas(fiesta.getCantEntradas() - fiestaDTO.getCantidadEntradas()); // actualiza
                                                                                                        // stock de
                                                                                                        // entradas

                    fiestaRepository.save(fiesta);
                }
            }
            return ordenDeCompraRepository.save(new OrdenDeCompra(user, fiestasDTO, montoTotal));
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