package com.example.kevin.poke;

import java.util.Random;

/**
 * Created by kevin on 2/7/2015.
 */
public class PokeMath {

    public final static Double MAX_IV = 31.0;
    public final static Double CONSTANT_HP = 10.0;
    public final static Double CONSTANT = 5.0;
    public final static Double LEVEL = 50.0;

    /**
     * Returns the actual number of HP the defending pokemon would lose. This method strictly
     * handles the math. Other methods will check for imunity. This method will only return the
     * max damage this attack will deal. Min damage is 85% of this method's return value.
     */
    public static Double calculateMaxDamage(int atkStat, int defStat, Pokemon poke1, Pokemon poke2,
                               Attack attack){
        Double maxDamage = 2 * (LEVEL / 5) + 2;
        maxDamage = maxDamage * atkStat * attack.getPower() / defStat;
        maxDamage /= 50;
        maxDamage += 2;
        maxDamage *= (stab(poke1, attack) * effectiveness(attack, poke2));
        maxDamage *= damageMultiplier(poke2, attack, Weather.CLEAR);
        return maxDamage;
    }

    public static Double calculateMaxDamage(int atkStat, int defStat, Pokemon poke1, Pokemon poke2,
                                            Attack attack, Weather weather){
        Double maxDamage = 2 * (LEVEL / 5) + 2;
        maxDamage = maxDamage * atkStat * attack.getPower() / defStat;
        maxDamage /= 50;
        maxDamage += 2;
        maxDamage *= (stab(poke1, attack) * effectiveness(attack, poke2));
        maxDamage *= damageMultiplier(poke2, attack, weather);
        return maxDamage;
    }

    /**
     * Returns the Max HP for the Pokemon. HP uses a separate method because it has a different
     * function to calculate its value. All IVs are assumed to be 31.
     */
    public static Double calculateHP(Pokemon poke, int effortHP){
        Double maxHP = 0.0;
        maxHP = MAX_IV + (2 * poke.getBaseHP()) + effortHP / 4;
        maxHP = maxHP * (LEVEL / 100) + CONSTANT_HP + LEVEL;
        return maxHP;
    }

    /**
     * Returns the final ATK, DEF, SPA, SPD, or SPE stat for a Pokemon after taking into account
     * the IVs, EVs, Nature, and all necessary conditions. All IVs are assumed to be 31.
     */
    public static Double calculateStat(Double baseStat, Double effort, Double natureMultiplier){
        double maxStat = MAX_IV + (2 * baseStat) + (effort / 4);
        maxStat = maxStat * (LEVEL / 100.0) + CONSTANT;
        maxStat *= natureMultiplier;
        return maxStat;
    }

    /**
     * Returns 1.5 if the attacking pokemon is the same type as the attack being used. Returns 1
     * otherwise.
     */
    public static Double stab(Pokemon poke, Attack attack){
        if(poke.type1.equals(attack.getType())
                || poke.type2.equals(attack.getType())){
            return 1.5;
        }
        return 1.0;
    }
    /**
     * Returns the multiplier ona certain attack from weather or status condition. Typing
     * multipliers will be handled by another method because of where they are in the equation
     * for calculating initial damage.
     */
    public static Double damageMultiplier(Pokemon poke, Attack atk, Weather weather){
        if (atk.getType().equalsIgnoreCase("Fire") && weather == Weather.SUNNY
                || atk.getType().equalsIgnoreCase("Water") && weather == Weather.RAIN
                || atk.getType().equalsIgnoreCase("Rock") && weather == Weather.SAND
                || atk.getType().equalsIgnoreCase("Ice") && weather == Weather.HAIL){
            return 1.5;
        }
        if (atk.getType().equalsIgnoreCase("Fire") && weather == Weather.RAIN
                || atk.getType().equalsIgnoreCase("Water") && weather == Weather.SUNNY
                || !atk.getPhysical() && weather == Weather.SAND){
            return 0.5;
        }
        return 1.0;
    }

    /**
     * Returns the "super-effective" / "not very effective" multiplier for an attack. The method
     * compares the attack tye against the both types of the defending pokemon.
     * @param attack
     * @param poke
     * @return
     */
    public static Double effectiveness(Attack attack, Pokemon poke) {
        String[] types = Pokemon.immunityChart.get(attack.getType());
        Double multiplier = 1.0;
        for (int i = 0; i < types.length; i++) {
            if (types[i].equalsIgnoreCase(poke.getType1())) {
                return 0.0;
            }
        }
        types = Pokemon.superEffectiveChart.get(attack.getType());
        for (int i = 0; i < types.length; i++) {
            if(types[i].equalsIgnoreCase(poke.getType1())
            || types[i].equalsIgnoreCase(poke.getType2())){
                multiplier *= 2;
            }
        }
        types = Pokemon.notVeryEffectiveChart.get(attack.getType());
        for (int i = 0; i < types.length; i++) {
            if(types[i].equalsIgnoreCase(poke.getType1())
                    || types[i].equalsIgnoreCase(poke.getType2())){
                multiplier /= 2;
            }
        }
        return multiplier;
    }
}
