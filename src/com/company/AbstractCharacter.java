package com.company;

public interface AbstractCharacter {
    void setStrength(int strength);
    void setIntelligence(int intelligence);
    void setDexterity(int dexterity);
    void setDefense(int defense);
    void setVitality(int vitality);
    void setWisdom(int wisdom);
    void setHealth(int health);
    void setMana(int mana);

    int getStrength();
    int getIntelligence();
    int getDexterity();
    int getDefense();
    int getVitality();
    int getWisdom();
    int getHealth();
    int getMana();
    String getRace();
    String getName();
}
