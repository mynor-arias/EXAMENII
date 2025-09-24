package org.example.attacks;

import org.example.exceptions.AttackMissedException;
import java.util.Random;

public class Attack {
    private String nombre;
    private int precision;
    private int basePower;
    private DamageRule damageRule;
    private Random random = new Random();

    public Attack(String nombre, int precision, int basePower, DamageRule damageRule) {
        this.nombre = nombre;
        this.precision = precision;
        this.basePower = basePower;
        this.damageRule = damageRule;
    }

    public int ejecutar() throws AttackMissedException {
        int chance = random.nextInt(100) + 1;

        if (chance > precision) {
            throw new AttackMissedException(nombre + " fallo ");
        }

        return damageRule.calculateDamage(basePower);
    }

    public String getNombre() {return nombre;}

    public int getPrecision() {return precision;}

    public int getBasePower() {return basePower;}

    public String toString() {
        return nombre + " (Poder: " + basePower + ", Precision: " + precision + "%)";
    }
}