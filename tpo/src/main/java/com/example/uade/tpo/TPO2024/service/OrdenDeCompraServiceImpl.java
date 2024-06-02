package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.exceptions.OrdenDuplicateException;
import com.example.uade.tpo.TPO2024.repository.OrdenDeCompraRepository;

@Service
public class OrdenDeCompraServiceImpl implements OrdenDeCompraService {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    public List<OrdenDeCompra> getOrdenes() {
        return ordenDeCompraRepository.findAll();
    }

    public Optional<OrdenDeCompra> getOrdenById(Long ordenId) {
        return ordenDeCompraRepository.findById(ordenId);
    }

    public OrdenDeCompra createOrden(Long ordenId, List<Fiesta> fiestas, int montoTotal)
            throws OrdenDuplicateException {
        List<OrdenDeCompra> ordenes = ordenDeCompraRepository.findAll();
        if (ordenes.stream().anyMatch(orden -> orden.getId().equals(ordenId)))
            throw new OrdenDuplicateException();
        return ordenDeCompraRepository.save(new OrdenDeCompra(ordenId, fiestas, montoTotal));
    }

    // public OrdenDeCompra updateOrden(Long ordenId)

    public void removeOrden(Long id) {
        ordenDeCompraRepository.deleteById(id);
    }

}