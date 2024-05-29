package com.example.uade.tpo.TPO2024.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La Fiesta que se intenta registrar ya existe.")
public class FiestaDuplicateException extends Exception {

}