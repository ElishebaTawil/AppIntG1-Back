package com.example.uade.tpo.TPO2024.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.TPO2024.dto.OrdenDeCompraRequest;
import com.example.uade.tpo.TPO2024.entity.OrdenDeCompra;
import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.FiestaNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.OrdenDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.OrdenNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;
import com.example.uade.tpo.TPO2024.service.OrdenDeCompraService;
import com.example.uade.tpo.TPO2024.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "*" }, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/ordenes")

public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraService ordenDeCompraService;

    @GetMapping
    public ResponseEntity<List<OrdenDeCompra>> getOrdenes() {
        return ResponseEntity.ok(ordenDeCompraService.getOrdenes());
    }

    @GetMapping("/{ordenId}")
    public ResponseEntity<OrdenDeCompra> getOrdenById(@PathVariable Long ordenId) throws OrdenNotFoundException {
        Optional<OrdenDeCompra> orden = ordenDeCompraService.getOrdenById(ordenId);
        if (orden.isPresent()) {
            return ResponseEntity.ok(orden.get());
        } else {
            throw new OrdenNotFoundException();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<OrdenDeCompra> createOrden(@RequestBody OrdenDeCompraRequest ordenRequest)
            throws OrdenDuplicateException, UserNotFoundException, FiestaNotFoundException {
        System.out.println("-----LLEGO EL FETCH------" + ordenRequest);
        OrdenDeCompra order = ordenDeCompraService.createOrden(
                ordenRequest.getEmail(),
                ordenRequest.getFiestas(),
                ordenRequest.getDescuento());
        return ResponseEntity.created(URI.create("/ordenes/" + order.getId())).body(order);
    }

    @DeleteMapping("/{ordenId}")
    public ResponseEntity<String> removeOrden(@PathVariable Long ordenId) {
        try {
            ordenDeCompraService.removeOrden(ordenId);
            return ResponseEntity.ok("Orden eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: La orden no existe.");
        }
    }
}
