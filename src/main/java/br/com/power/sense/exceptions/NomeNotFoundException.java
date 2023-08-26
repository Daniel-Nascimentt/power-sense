package br.com.power.sense.exceptions;

public class NomeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public NomeNotFoundException() {
    }

    public NomeNotFoundException(String message) {
        super(message);
    }

}
