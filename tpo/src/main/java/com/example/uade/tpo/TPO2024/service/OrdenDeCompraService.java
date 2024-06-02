package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.exceptions.OrdenDuplicateException;

public interface OrdenDeCompraService {

    public List<OrdenDeCompra> getOrdenes();

    public Optional<OrdenDeCompra> getOrdenById(Long ordenId);

    public OrdenDeCompra createOrden(Long ordenId, List<Fiesta> fiestas, int montoTotal)
            throws OrdenDuplicateException;

    // public OrdenDeCompra updateOrden(Long id);

    public void removeOrden(Long id);
}
