package com.example.uade.tpo.TPO2024.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.OrdenDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.service.OrdenDeCompraService;
import com.example.uade.tpo.TPO2024.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ordenes")

public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraService ordenDeCompraService;

    @GetMapping
    public ResponseEntity<List<OrdenDeCompra>> getOrdenes() {
        return ResponseEntity.ok(ordenDeCompraService.getOrdenes());
    }

    @GetMapping("/{ordenId}")
    public ResponseEntity<OrdenDeCompra> getOrdenById(@PathVariable Long ordenId) {
        Optional<OrdenDeCompra> result = ordenDeCompraService.getOrdenById(ordenId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createOrden(@RequestBody OrdenDeCompra ordenRequest)
            throws OrdenDuplicateException {
        OrdenDeCompra result = ordenDeCompraService.createOrden(ordenRequest.getId(), ordenRequest.getIdUsuario(),
                ordenRequest.getFiestas(), ordenRequest.getMontoTotal());
        return ResponseEntity.created(URI.create("/ordenes/" + result.getId())).body(result);
    }

}
