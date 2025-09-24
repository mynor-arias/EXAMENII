package org.example.game;

public class BattleEvent {
    private String actor;
    private String accion;
    private int damage;
    private String objetivo;

    public BattleEvent(String actor, String accion, int damage, String objetivo) {
        this.actor = actor;
        this.accion = accion;
        this.damage = damage;
        this.objetivo = objetivo;
    }

    public BattleEvent(String actor, String accion, String objetivo) {
        this(actor, accion, 0, objetivo);
    }

    public String getActor() {return actor;}

    public String getAccion() {return accion;}

    public int getDamage() {return damage;}

    public String getObjetivo() {return objetivo;}

    public String toString() {
        if (damage > 0) {
            return actor + " " + accion + " causando " + damage + " de daño";
        } else {
            return actor + " " + accion;
        }
    }
}