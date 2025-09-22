package com.library.recetas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OperationNotAllowedException extends RecetaAppException {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
