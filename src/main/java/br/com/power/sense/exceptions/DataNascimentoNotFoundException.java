package br.com.power.sense.exceptions;

public class DataNascimentoNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public DataNascimentoNotFoundException() {
    }

    public DataNascimentoNotFoundException(String message) {
        super(message);
    }

}
