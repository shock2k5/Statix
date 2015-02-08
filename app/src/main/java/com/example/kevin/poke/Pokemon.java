package com.example.kevin.poke;

import android.media.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Pokemon {

    public static HashMap<String, String[]> superEffectiveChart;
    public static HashMap<String, String[]> notVeryEffectiveChart;
    public static HashMap<String, String[]> immunityChart;

    /**
     * This static initialization block loads all supereffective typing and imunities into 2
     * Hashmaps so I can check during damage calculation. The key is the attacking move's type.
     * The value is a String[] of all the types that are of the appropriate effectiveness.
     */
    static {
        superEffectiveChart = new HashMap<>();
        notVeryEffectiveChart = new HashMap<>();
        immunityChart = new HashMap<>();
        superEffectiveChart.put("Normal", new String[]{});
        superEffectiveChart.put("Fighting", new String[]{"Normal", "Rock", "Steel", "Ice", "Dark"});
        superEffectiveChart.put("Flying", new String[]{"Fighting", "Bug", "Grass"});
        superEffectiveChart.put("Poison", new String[]{"Grass", "Fairy"});
        superEffectiveChart.put("Ground", new String[]{"Poison", "Steel", "Fire", "Rock",
                "Electric"});
        superEffectiveChart.put("Rock", new String[]{"Flying", "Bug", "Fire", "Ice"});
        superEffectiveChart.put("Bug", new String[]{"Psychic", "Dark", "Grass"});
        superEffectiveChart.put("Ghost", new String[]{"Ghost", "Psychic"});
        superEffectiveChart.put("Steel", new String[]{"Rock", "Ice", "Fairy"});
        superEffectiveChart.put("Fire", new String[]{"Bug", "Steel", "Grass", "Ice"});
        superEffectiveChart.put("Water", new String[]{"Ground", "Rock", "Fire"});
        superEffectiveChart.put("Grass", new String[]{"Ground", "Rock", "Water"});
        superEffectiveChart.put("Electric", new String[]{"Flying", "Water"});
        superEffectiveChart.put("Psychic", new String[]{"Fighting", "Poison"});
        superEffectiveChart.put("Ice", new String[]{"Flying", "Ground", "Grass", "Dragon"});
        superEffectiveChart.put("Dragon", new String[]{"Dragon"});
        superEffectiveChart.put("Dark", new String[]{"Ghost", "Psychic"});
        superEffectiveChart.put("Fairy", new String[]{"Fighting", "Dragon", "Dark"});

        notVeryEffectiveChart.put("Normal", new String[]{"Rock", "Steel"});
        notVeryEffectiveChart.put("Fighting", new String[]{"Flying", "Poison", "Bug", "Psychic",
                "Fairy"});
        notVeryEffectiveChart.put("Flying", new String[]{"Rock", "Steel", "Electric"});
        notVeryEffectiveChart.put("Poison", new String[]{"Poison", "Ground", "Rock", "Ghost"});
        notVeryEffectiveChart.put("Ground", new String[]{"Bug", "Grass"});
        notVeryEffectiveChart.put("Rock", new String[]{"Fighting", "Ground", "Steel"});
        notVeryEffectiveChart.put("Bug", new String[]{"Fighting", "Flying", "Poison", "Ghost",
                "Steel", "Fire", "Fairy"});
        notVeryEffectiveChart.put("Ghost", new String[]{"Dark"});
        notVeryEffectiveChart.put("Steel", new String[]{"Steel", "Fire", "Water", "Electric"});
        notVeryEffectiveChart.put("Fire", new String[]{"Rock", "Fire", "Water", "Dragon"});
        notVeryEffectiveChart.put("Water", new String[]{"Water", "Grass", "Dragon"});
        notVeryEffectiveChart.put("Grass", new String[]{"Flying", "Poison", "Bug", "Steel",
                "Fire", "Grass", "Dragon"});
        notVeryEffectiveChart.put("Electric", new String[]{"Grass", "Electric", "Dragon"});
        notVeryEffectiveChart.put("Psychic", new String[]{"Steel", "Psychic"});
        notVeryEffectiveChart.put("Ice", new String[]{"Fire", "Ice", "Water", "Steel"});
        notVeryEffectiveChart.put("Dragon", new String[]{"Steel"});
        notVeryEffectiveChart.put("Dark", new String[]{"Fighting", "Dark", "Fairy"});
        notVeryEffectiveChart.put("Fairy", new String[]{"Poison", "Steel", "Fire"});

        immunityChart = new HashMap<>();
        immunityChart.put("Normal", new String[]{"Ghost"});
        immunityChart.put("Fighting", new String[]{"Ghost"});
        immunityChart.put("Flying", new String[]{});
        immunityChart.put("Poison", new String[]{"Steel"});
        immunityChart.put("Ground", new String[]{"Flying"});
        immunityChart.put("Rock", new String[]{});
        immunityChart.put("Bug", new String[]{});
        immunityChart.put("Ghost", new String[]{"Normal"});
        immunityChart.put("Steel", new String[]{});
        immunityChart.put("Fire", new String[]{});
        immunityChart.put("Water", new String[]{});
        immunityChart.put("Grass", new String[]{});
        immunityChart.put("Electric", new String[]{"Ground"});
        immunityChart.put("Psychic", new String[]{"Dark"});
        immunityChart.put("Ice", new String[]{});
        immunityChart.put("Dragon", new String[]{"Fairy"});
        immunityChart.put("Dark", new String[]{});
        immunityChart.put("Fairy", new String[]{});
    }

    String name, type1, type2, status;
    int baseHP, baseAtk, baseDef, baseSpA, baseSpD, baseSpe, iconID;
    String[] abilities;
    static List<Pokemon> allPokes = new ArrayList<>();
    static List<String> names = new ArrayList<String>();
    String[] attacks;
    Image image;
    static int population = 2;

    static Pokemon CHARIZARD = new Pokemon("Charizard", "Fire", "Flying", 78, 84, 78, 109, 85, 100,
            new String[] {"Blaze", "Solar Power"}, R.drawable.charizard);
    static Pokemon SYLVEON = new Pokemon("Sylveon", "Fairy", "none", 95, 65, 65, 110, 130, 60,
            new String[] {"Cute Charm", "Pixalate"}, R.drawable.sylveon);
    static Pokemon BISHARP = new Pokemon("Bisharp", "Dark", "Steel", 65, 125, 100, 60, 70, 70,
            new String[] {"Defiant", "Inner Focus", "Pressure",}, R.drawable.bisharp,
            new String[] {"Iron Head", "Metal Burst", "Fury Cutter", "Feint Attack",
                    "Metal Claw", "Night Slash"});
    static Pokemon HEATRAN = new Pokemon("Heatran", "Fire", "Steel", 91, 90, 106, 130, 106, 77,
            new String[] {"Flash Fire", "Flame Body"}, R.drawable.heatran);
    static Pokemon BLASTOISE = new Pokemon("Blastoise", "Water", "none", 79, 103, 120, 135, 115, 78,
            new String[] {"Torrent", "Rain Dish"}, R.drawable.blastoise);
    static Pokemon KANGASKHAN = new Pokemon("Kangaskhan", "Normal", "none", 105, 95, 80, 40, 80, 90,
            new String[] {"Defiant", "Inner Focus", "Pressure",}, R.drawable.kangaskhan);


    static{
        allPokes.add(BISHARP);
        allPokes.add(BLASTOISE);
        allPokes.add(CHARIZARD);
        allPokes.add(HEATRAN);
        allPokes.add(KANGASKHAN);
        allPokes.add(SYLVEON);

        names.add("BISHARP");
        names.add("BLASTOISE");
        names.add("CHARIZARD");
        names.add("HEATRAN");
        names.add("KANGASKHAN");
        names.add("SYLVEON");

    }

    public Pokemon(String name, String type1, String type2, int baseHP, int baseAtk, int baseDef,
                   int baseSpA, int baseSpD, int baseSpe, String[] abilities, int iconID) {

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
        this.iconID = iconID;
        attacks = new String[100];
    }

    public Pokemon(String name, String type1, String type2, int baseHP, int baseAtk, int baseDef,
                   int baseSpA, int baseSpD, int baseSpe, String[] abilities, int iconID,
                   String[] attacks) {

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
        this.iconID = iconID;
        this.attacks = attacks;
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

    public int compareTo(Pokemon other){
        return this.name.compareTo(other.name);
    }

    public static Pokemon getPokemon(String name){
        for(Pokemon pkm : Pokemon.allPokes){
            if (pkm.getName().equalsIgnoreCase(name)){
                return pkm;
            }
        }
        return null;
    }
}
