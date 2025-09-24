package org.example.pokemon;

import org.example.attacks.Attack;
import java.util.List;
import java.util.ArrayList;

public abstract class Pokemon {
    protected String nombre;
    protected String tipo;
    protected int hpMaximo;
    protected int hpActual;
    protected List<Attack> ataques;

    public Pokemon(String nombre, String tipo, int hpMaximo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.hpMaximo = hpMaximo;
        this.hpActual = hpMaximo;
        this.ataques = new ArrayList<>();
    }

    public void recibirDamage(int damage) {
        hpActual -= damage;
        if (hpActual < 0) {
            hpActual = 0;
        }
    }

    public boolean estaDebilitado() {return hpActual <= 0;}

    public void aprenderAtaque(Attack ataque) {ataques.add(ataque);}

    public abstract void inicializarAtaques();

    public void entrarBatalla() {
        System.out.println(nombre + " esta listo para la batalla ");
    }

    public String getNombre() {return nombre;}

    public String getTipo() {return tipo;}

    public int getHpMaximo() {return hpMaximo;}

    public int getHpActual() {return hpActual;}

    public List<Attack> getAtaques() {return ataques;}

    public String toString() {
        return nombre + " (" + tipo + ") - HP: " + hpActual + "/" + hpMaximo;
    }
}

