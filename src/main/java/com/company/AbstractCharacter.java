package com.company;

import java.util.ArrayList;

public interface AbstractCharacter {
    void setStrength(long strength);
    void setIntelligence(long intelligence);
    void setDexterity(long dexterity);
    void setDefense(long defense);
    void setVitality(long vitality);
    void setWisdom(long wisdom);
    void setHealth(long health);
    void setMana(long mana);
    void setXp(long xp);
    void setLevel(long level);
    void setRace(String race);
    void setName(String name);
    void setClassification(String classification);
    void setInventory(ArrayList<String> inventory);

    long getStrength();
    long getIntelligence();
    long getDexterity();
    long getDefense();
    long getVitality();
    long getWisdom();
    long getHealth();
    long getMana();
    long getXp();
    long getLevel();
    ArrayList<String> getInventory();
    String getRace();
    String getName();
    String getClassification();
}
