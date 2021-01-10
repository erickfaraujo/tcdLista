package com.api.tcdLista.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "O conteúdo informado já está na lista")
public class ConteudoAlreadyExistsException extends RuntimeException {

}
