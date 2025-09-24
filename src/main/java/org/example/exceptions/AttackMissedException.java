package org.example.exceptions;

public class AttackMissedException extends Exception {
    public AttackMissedException(String mensaje) {
        super(mensaje);
    }

    public AttackMissedException() {
        super("El ataque fallo");
    }
}


