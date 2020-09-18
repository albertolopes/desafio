package com.beto.desafio.exceptions;

public class IllegalAccessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalAccessException(String msg) {
        super(msg);
    }

    public IllegalAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}