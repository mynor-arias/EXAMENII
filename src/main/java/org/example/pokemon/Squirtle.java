package org.example.pokemon;

import org.example.attacks.Attack;
import org.example.attacks.DamageRule;

public class Squirtle extends Pokemon {

    public Squirtle() {
        super("Squirtle", "Agua", 100);
        inicializarAtaques();
    }

    public void inicializarAtaques() {
        DamageRule tackleRule = (power) -> power + 3;
        Attack tackle = new Attack("Placaje", 95, 15, tackleRule);
        aprenderAtaque(tackle);

        DamageRule defendRule = (power) -> power - 2;
        Attack defend = new Attack("Pistola Agua", 100, 10, defendRule);
        aprenderAtaque(defend);

        DamageRule waterGunRule = (power) -> power + 6;
        Attack waterGun = new Attack("Mordisco", 90, 18, waterGunRule);
        aprenderAtaque(waterGun);

        DamageRule hydroPumpRule = (power) -> power + 10;
        Attack hydroPump = new Attack("Hidrobomba", 75, 32, hydroPumpRule);
        aprenderAtaque(hydroPump);
    }


}