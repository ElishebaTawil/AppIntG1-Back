package com.example.uade.tpo.TPO2024.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El usuario con ese id no existe.")
public class UserNotFoundException extends Exception {

}
