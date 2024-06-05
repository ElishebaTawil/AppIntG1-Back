package com.example.uade.tpo.TPO2024.controllers;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.FiestaNotFoundException;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserNotFoundException;
import com.example.uade.tpo.TPO2024.service.FiestaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/fiestas")
public class FiestaController {

    @Autowired
    private FiestaServiceImpl fiestaService;

    @GetMapping
    public List<Fiesta> getAllProducts() {
        return fiestaService.getFiestas();
    }

    @GetMapping("/{fiestaId}")
    public ResponseEntity<Fiesta> getFiestaById(@PathVariable Long fiestaId) throws FiestaNotFoundException {
        Optional<Fiesta> fiesta = fiestaService.getFiestaById(fiestaId);
        if (fiesta.isPresent()) {
            return ResponseEntity.ok(fiesta.get());
        } else {
            throw new FiestaNotFoundException();
        }
    }

    @GetMapping("/price/{precioMaximo}")
    public List<Fiesta> getFiestasPorPrecio(@PathVariable int precioMaximo) {
        return fiestaService.getFiestasPorPrecio(precioMaximo);
    }

    @GetMapping("/ordenadas/menorAmayor")
    public List<Fiesta> getFiestasOrdenadasPorPrecioDeMenorAMayor() {
        return fiestaService.getFiestasOrdenadasPorPrecioDeMenorAMayor();
    }

    @GetMapping("/ordenadas/mayorAmenor")
    public List<Fiesta> getFiestasOrdenadasPorPrecioDeMayorAMenor() {
        return fiestaService.getFiestasOrdenadasPorPrecioDeMayorAMenor();
    }

    @PostMapping("/agregar")
    public ResponseEntity<Object> createFiesta(@RequestBody Fiesta fiestaRequest)
            throws FiestaDuplicateException {
        Fiesta result = fiestaService.createFiesta(fiestaRequest.getName(),
                fiestaRequest.getFecha(), fiestaRequest.getUbicacion(), fiestaRequest.getImage(),
                fiestaRequest.getPrice(), fiestaRequest.getCantEntradas(), fiestaRequest.isAvailable());
        return ResponseEntity.created(URI.create("/fiestas/" + result.getId())).body(result);
    }

    @PutMapping("/{fiestaId}")
    public ResponseEntity<Fiesta> updateFiesta(@PathVariable Long fiestaId, @RequestBody Fiesta fiestaActualizada)
            throws FiestaNotFoundException {
        try {
            Fiesta partyActualizada = fiestaService.updateFiesta(fiestaId, fiestaActualizada);
            return ResponseEntity.ok(partyActualizada);
        } catch (FiestaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El id de la fiesta no existe", e);
        }
    }

    @DeleteMapping("/{fiestaId}")
    public ResponseEntity<String> removeFiesta(@PathVariable Long fiestaId) {
        try {
            fiestaService.removeFiesta(fiestaId);
            return ResponseEntity.ok("Fiesta eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: la fiesta no existe.");
        }
    }

    @PostMapping("/{fiestaId}/uploadImage")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String folder = "upload/images/";
        try {
            byte[] bytes = file.getBytes();
            Path directoryPath = Paths.get(folder);

            // Ensure directory existence
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Path path = directoryPath.resolve(file.getOriginalFilename());
            Files.write(path, bytes);
            return ResponseEntity.ok("File uploaded successfully: " + path.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }
}