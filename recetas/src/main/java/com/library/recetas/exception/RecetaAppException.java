package com.library.recetas.exception;

public class RecetaAppException extends RuntimeException {
    public RecetaAppException(String message) {
        super(message);
    }

    public RecetaAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
