package br.com.power.sense.exceptions;

public class SexoNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SexoNotFoundException() {
    }

    public SexoNotFoundException(String message) {
        super(message);
    }

}
