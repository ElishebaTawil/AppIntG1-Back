package com.example.uade.tpo.TPO2024.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La fiesta con ese id no existe.")
public class FiestaNotFoundException extends Exception {

}
