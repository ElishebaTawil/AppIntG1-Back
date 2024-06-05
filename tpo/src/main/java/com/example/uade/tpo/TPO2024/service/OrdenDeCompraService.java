package com.example.uade.tpo.TPO2024.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.TPO2024.dto.FiestaAsociadaDTO;
import com.example.uade.tpo.TPO2024.dto.FiestaDTO;
import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.FiestaAsociada;
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.exceptions.FiestaNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.OrdenDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.OrdenNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;

public interface OrdenDeCompraService {

    public List<OrdenDeCompra> getOrdenes();

    public Optional<OrdenDeCompra> getOrdenById(Long ordenId);

    OrdenDeCompra createOrden(Long userId, List<FiestaDTO> fiestas)
            throws OrdenDuplicateException, UserNotFoundException, FiestaNotFoundException;

    // public OrdenDeCompra updateOrden(Long id);

    public void removeOrden(Long ordenId) throws OrdenNotFoundException;
}
