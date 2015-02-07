package com.example.kevin.poke;

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
     * handles the math. Other methods will check for imunity.
     */
    public int calculateDamage(int atkStat, int defStat, Pokemon poke1, Pokemon poke2,
                               Attack attack){

        return 0;
    }

    /**
     * Returns the Max HP for the Pokemon. HP uses a separate method because it has a different
     * function to calculate its value. All IVs are assumed to be 31.
     */
    public Double calculateHP(Pokemon poke, int effortHP){
        Double maxHP = 0.0;
        maxHP = MAX_IV + (2 * poke.getBaseHP()) + effortHP / 4;
        maxHP = maxHP * (LEVEL / 100) + CONSTANT_HP + LEVEL;
        return maxHP;
    }

    /**
     * Returns the final ATK, DEF, SPA, SPD, or SPE stat for a Pokemon after taking into account
     * the IVs, EVs, Nature, and all necessary conditions. All IVs are assumed to be 31.
     */
    public Double calculateStat(Double baseStat, Double effort, Double natureMultiplier){
        double maxStat = MAX_IV + (2 * baseStat) + (effort / 4);
        maxStat = maxStat * (LEVEL / 100.0) + CONSTANT;
        maxStat *= natureMultiplier;
        return maxStat;
    }

    /**
     * Returns 1.5 if the attacking pokemon is the same type as the attack being used. Returns 1
     * otherwise.
     */
    public Double stab(String pokeType, String atkType){
        if(pokeType.equals(atkType)) return 1.5;
        return 1.0;
    }
    /**
     * Returns the multiplier ona certain attack from weather or status condition. Typing
     * multipliers will be handled by another method because of where they are in the equation
     * for calculating initial damage.
     */
    public double damageMultiplier(){
        //TODO
        return 0.0;
    }


}
