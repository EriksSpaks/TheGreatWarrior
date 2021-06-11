package com.company;

public abstract class Character implements AbstractCharacter{
    //Attributes
    private long strength;           // Attack strength
    private long intelligence;       // Magic attack strength
    private long dexterity;          // Attack speed
    private long defense;            // Armor
    private long vitality;           // Health regeneration speed
    private long wisdom;             // Mana regeneration speed
    private long health;
    private long mana;
    private long level = 0;
    private long xp = 0;
    //Parameters
    private String race;            // Human, Orc, Elf
    private String name;

    public Character(long strength, long intelligence, long dexterity, long defense, long vitality, long wisdom, long health, long mana, String race, String name) {
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

    public void setLevel(long level) {
        this.level = level;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public void setStrength(long strength) {
        this.strength = strength;
    }

    public void setIntelligence(long longelligence) {
        this.intelligence = intelligence;
    }

    public void setDexterity(long dexterity) {
        this.dexterity = dexterity;
    }

    public void setDefense(long defense) {
        this.defense = defense;
    }

    public void setVitality(long vitality) {
        this.vitality = vitality;
    }

    public void setWisdom(long wisdom) {
        this.wisdom = wisdom;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public void setMana(long mana) {
        this.mana = mana;
    }

    public long getStrength() {
        return strength;
    }

    public long getIntelligence() {
        return intelligence;
    }

    public long getDexterity() {
        return dexterity;
    }

    public long getDefense() {
        return defense;
    }

    public long getVitality() {
        return vitality;
    }

    public long getWisdom() {
        return wisdom;
    }

    public long getHealth() {
        return health;
    }

    public long getMana() {
        return mana;
    }

    public long getLevel() {
        return level;
    }

    public long getXp() {
        return xp;
    }

    public String getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

}
