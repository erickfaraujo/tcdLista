package com.api.tcdLista.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Dados inválidos")
public class InvalidRequestException extends RuntimeException {

}
