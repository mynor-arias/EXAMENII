package org.example.pokemon;



import org.example.attacks.Attack;
import org.example.attacks.DamageRule;

public class Pikachu extends Pokemon {

    public Pikachu() {
        super("Pikachu", "Electrico", 100);
        inicializarAtaques();
    }

    public void inicializarAtaques() {
        DamageRule quickRule = (power) -> power + 4;
        Attack quickAttack = new Attack("Ataque Rápido", 95, 12, quickRule);
        aprenderAtaque(quickAttack);

        DamageRule tailRule = (power) -> power;
        Attack tailWhip = new Attack("Gruñido", 92, 10, tailRule);
        aprenderAtaque(tailWhip);

        DamageRule thunderShockRule = (power) -> power + 8;
        Attack thunderShock = new Attack("Rayo", 85, 20, thunderShockRule);
        aprenderAtaque(thunderShock);

        DamageRule thunderRule = (power) -> power + 18;
        Attack thunder = new Attack("Impactrueno", 60, 45, thunderRule);
        aprenderAtaque(thunder);
    }


}