package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allproducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addproduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/removeproduct")
    public ResponseEntity<?> removeProduct(@RequestBody String id) {
        productService.removeProduct(id);
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
