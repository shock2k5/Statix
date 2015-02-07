package com.example.kevin.poke;

/**
 * Created by kevin on 2/7/2015.
 */
public class Pokemon {

    String name, type1, type2;
    int baseHP, baseAtk, baseDef, baseSpA, baseSpD, baseSpe;
    String[] abilities;

    static Pokemon CHARIZARD = new Pokemon("Charizard", "Fire", "Flying", 78, 84, 78, 109, 85, 100,
            new String[] {"Blaze", "Solar Power"});
    static Pokemon SYLVEON = new Pokemon("Sylveon", "Fairy", "none", 95, 65, 65, 110, 130, 60,
            new String[] {"Cute Charm", "Pixalate"});


    public Pokemon(String name, String type1, String type2, int baseHP, int baseAtk, int baseDef,
                   int baseSpA, int baseSpD, int baseSpe, String[] abilities) {

        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.baseHP = baseHP;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseSpA = baseSpA;
        this.baseSpD = baseSpD;
        this.baseSpe = baseSpe;
        this.abilities = abilities;
    }

    public int getBaseHP(){
        return this.baseHP;
    }

    public String getName() {
        return name;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public int getBaseSpA() {
        return baseSpA;
    }

    public int getBaseSpD() {
        return baseSpD;
    }

    public int getBaseSpe() {
        return baseSpe;
    }

    public String[] getAbilities() {
        return abilities;
    }
}
