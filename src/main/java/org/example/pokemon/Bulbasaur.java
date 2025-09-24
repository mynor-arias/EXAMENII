package org.example.pokemon;

import org.example.attacks.Attack;
import org.example.attacks.DamageRule;

public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super("Bulbasaur", "Planta", 100);
        inicializarAtaques();
    }

    public void inicializarAtaques() {
        DamageRule tackleRule = (power) -> power + 2;
        Attack tackle = new Attack("Placaje", 88, 15, tackleRule);
        aprenderAtaque(tackle);

        DamageRule leechRule = (power) -> power + 1;
        Attack leechSeed = new Attack("Polvo Veneno", 95, 12, leechRule);
        aprenderAtaque(leechSeed);

        DamageRule vineWhipRule = (power) -> power + 7;
        Attack vineWhip = new Attack("Bomba Germen", 88, 22, vineWhipRule);
        aprenderAtaque(vineWhip);

        DamageRule solarBeamRule = (power) -> power + 15;
        Attack solarBeam = new Attack("Hoja Afilada", 65, 40, solarBeamRule);
        aprenderAtaque(solarBeam);
    }


}