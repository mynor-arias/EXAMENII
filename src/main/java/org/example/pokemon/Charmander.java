package org.example.pokemon;

import org.example.attacks.Attack;
import org.example.attacks.DamageRule;

public class Charmander extends Pokemon {

    public Charmander() {
        super("Charmander", "Fuego", 100);
        inicializarAtaques();
    }

    public void inicializarAtaques() {
        DamageRule tackleRule = (power) -> power + 5;
        Attack tackle = new Attack("Cuchillada", 90, 15, tackleRule);
        aprenderAtaque(tackle);

        DamageRule growlRule = (power) -> power;
        Attack growl = new Attack("Arañazo", 100, 8, growlRule);
        aprenderAtaque(growl);

        DamageRule emberRule = (power) -> power + 8;
        Attack ember = new Attack("Puño Fuego", 85, 20, emberRule);
        aprenderAtaque(ember);

        DamageRule fireBlastRule = (power) -> power + 12;
        Attack fireBlast = new Attack("Lanzallamas", 70, 35, fireBlastRule);
        aprenderAtaque(fireBlast);
    }


}