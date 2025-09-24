package org.example.attacks;

@FunctionalInterface
public interface DamageRule {
    int calculateDamage(int basePower);
}