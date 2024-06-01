package com.managechurch.services.exceptions;

public class DataItegrityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataItegrityException(String message) {
        super(message);
    }

    public DataItegrityException() {
        super("Não foi possível salvar a informação, certifique-se de que informou todos os dados corretamente.");
    }

}
