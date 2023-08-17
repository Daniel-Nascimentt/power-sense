package br.com.power.sense.exceptions;

public class CpfNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CpfNotFoundException() {
	}

	public CpfNotFoundException(String message) {
		super(message);
	}

}
