package org.example.exceptions;

public class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String mensaje) {
        super(mensaje);
    }

    public InvalidChoiceException() {
        super("Opcion invalida");
    }
}


