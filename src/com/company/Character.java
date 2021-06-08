package com.company;

public class Character implements AbstractCharacter{
    //Attributes
    private int strength;           // Attack strength
    private int intelligence;       // Magic attack strength
    private int dexterity;          // Attack speed
    private int defense;            // Armor
    private int vitality;           // Health regeneration speed
    private int wisdom;             // Mana regeneration speed
    private int health;
    private int mana;
    //Parameters
    private String race;            // Human, Orc, Elf
    private String name;

    public Character(int strength, int intelligence, int dexterity, int defense, int vitality, int wisdom, int health, int mana, String race, String name) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.defense = defense;
        this.vitality = vitality;
        this.wisdom = wisdom;
        this.health = health;
        this.mana = mana;
        this.race = race;
        this.name = name;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getDefense() {
        return defense;
    }

    public int getVitality() {
        return vitality;
    }

    public int getWisdom() {
        return wisdom;
    }

    public String getRace() {
        return race;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
    }

}
