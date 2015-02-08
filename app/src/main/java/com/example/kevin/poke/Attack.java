package com.example.kevin.poke;

import java.util.ArrayList;
import java.util.List;


public class Attack {

    public final static Attack EARTHQUAKE = new Attack("Earthquake", "Ground", 100, 100, 10,
            true, true);
    public final static Attack FAKE_OUT = new Attack("Fake Out", "Normal", 40, 100, 10, false,
            true);
    public final static Attack FEINT_ATTACK = new Attack("Feint Attack", "Dark", 60, 100, 10,
            false, true);

    public final static Attack FIRE_BLAST = new Attack("Fire Blast", "Fire", 110, 85, 5, false,
            false);
    public final static Attack FLASH_CANNON = new Attack("Flash Cannon", "Steel", 80, 100, 10,
            false, false);
    public final static Attack FURY_CUTTER = new Attack("Fury Cutter", "Bug", 40, 95, 20,
            false, true);

    public final static Attack HEAT_WAVE = new Attack("Heat Wave", "Fire", 95, 90, 10, true, false);
    public final static Attack HYDRO_PUMP = new Attack("Hydro Pump", "Water", 120, 80, 5, false,
            false);
    public final static Attack HYPER_VOICE = new Attack("Hyper Voice", "Fairy", 117, 100, 10,
            true, false);
    public final static Attack IRON_HEAD = new Attack("Iron Head", "Steel", 80, 100, 15, false,
            true);
    public final static Attack MEATL_CLAW = new Attack("Metal Claw", "Steel", 50, 95, 5,
            false, true);
    public final static Attack NIGHT_SLASH = new Attack("Night Slash", "Dark", 70, 100, 15,
            false, true);
    public final static Attack SOLAR_BEAM = new Attack("Solar Beam", "Grass", 120, 100, 10, true,
            false);
    public final static Attack SUCKER_PUNCH = new Attack("Sucker Punch", "Dark", 80, 100, 5,
            false, true);

    public static ArrayList<Attack> allAttacks = new ArrayList<>();
    public static String[] atkNames = {"Earthquake", "Fake Out", "Feint Attack", "Flash Cannon",
            "Fury Cutter", "Heat Wave", "Hydro Pump", "Hyper Voice", "Iron Head", "Metal Burst",
            "Metal Claw", "Night Slash", "Solar Beam", "Sucker Punch"};

    static {

        allAttacks.add(EARTHQUAKE);
        allAttacks.add(FAKE_OUT);
        allAttacks.add(FLASH_CANNON);
        allAttacks.add(HEAT_WAVE);
        allAttacks.add(HYDRO_PUMP);
        allAttacks.add(HYPER_VOICE);
        allAttacks.add(IRON_HEAD);
        allAttacks.add(SOLAR_BEAM);
        allAttacks.add(SUCKER_PUNCH);

    }

    private String name, type;
    private int accuracy, power, pp;
    private boolean spread, physical;

    private Attack(String name, String type, int power, int accuracy, int pp, boolean spread,
                   boolean physical) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.spread = spread;
        this.physical = physical;
    }

    public String getName(){
        return this.name;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getPp() {
        return pp;
    }

    public static Attack getAttack(String name){
        for (Attack atk : allAttacks){
            if(name.equalsIgnoreCase(atk.name)){
                return atk;
            }
        }
        return null;
    }

    public boolean getPhysical(){
        return this.physical;
    }
}
