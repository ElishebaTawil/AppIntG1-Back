package com.example.uade.tpo.TPO2024.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El mail que se intenta registrar ya se encuentra en uso.")
public class UserDuplicateException extends Exception {

}
