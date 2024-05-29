package com.example.uade.tpo.TPO2024.controllers;

import com.example.uade.tpo.TPO2024.entity.Fiesta;
import com.example.uade.tpo.TPO2024.entity.User;
import com.example.uade.tpo.TPO2024.exceptions.FiestaDuplicateException;
import com.example.uade.tpo.TPO2024.exceptions.UserDuplicateException;
import com.example.uade.tpo.TPO2024.service.FiestaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("fiestas")
public class FiestaController {

    @Autowired
    private FiestaServiceImpl fiestaService;

    @GetMapping("/")
    public String home() {
        return "Spring Boot App is running";
    }

    @GetMapping("/allfiestas")
    public List<Fiesta> getAllProducts() {
        return fiestaService.getFiestas();
    }

    @PostMapping
    public ResponseEntity<Object> createFiesta(@RequestBody Fiesta fiestaRequest)
            throws FiestaDuplicateException {
        Fiesta result = fiestaService.createFiesta(fiestaRequest.getName(),
                fiestaRequest.getImage(), fiestaRequest.getNewPrice(), fiestaRequest.isAvailable());
        return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
    }

    @DeleteMapping("/{fiestaId}")
    public ResponseEntity<?> removeFiesta(@RequestBody Long fiestaId) {
        fiestaService.removeFiesta(fiestaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
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