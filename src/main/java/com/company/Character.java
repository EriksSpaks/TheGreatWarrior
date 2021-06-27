package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Character implements AbstractCharacter{
    //Attributes
    private long strength;           // Attack strength
    private long intelligence;       // Magic attack strength
    private long defense;            // Armor
    private long vitality;           // Health regeneration speed
    private long wisdom;             // Mana regeneration speed
    private long health;
    private long mana;
    private long level = 1;
    private long xp = 0;
    //Parameters
    private String race;            // Human, Orc, Elf
    private String name;
    private String classification;
    //inventory
    private ArrayList<String> inventory = new ArrayList<>();
    //additional variables
    private boolean trainingCompleted = false;
    private long xpToNextLvl = 200;

    public Character(long strength, long intelligence, long defense, long vitality, long wisdom, long health, long mana, String race, String name, String classification) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.defense = defense;
        this.vitality = vitality;
        this.wisdom = wisdom;
        this.health = health;
        this.mana = mana;
        this.race = race;
        this.name = name;
        this.classification = classification;
    }

    public void itemUpgrade(){
        // TODO: 6/19/2021 item upgrade mechanics and save/load inventory methods(with xml files)
        /*
         * Если 10 одинаковых мечей(или волшебных палочек если маг) в инвентаре, то можно превратить их в улучшенную версию этого меча(палочки если маг)
         * 10 x wooden sword -> 1 bronze sword; 10 x bronze sword -> 1 iron sword; 10 iron sword -> 1 gold sword; 10 x gold sword -> 1 legendary sword
         * У всех мечей разные характеристики:
         * wooden sword: +1 strength
         * bronze sword: +2 strength
         * iron sword: strength + 2 + character level + 1
         * gold sword: strength + 3 + character level + 3
         * legendary sword: strength + 5 + character level + defense level + 5
         */
        int countWSword = 0;
        int countBSword = 0;
        int countISword = 0;
        int countGSword = 0;
        int countWBow = 0;
        int countBBow = 0;
        int countIBow = 0;
        int countGBow = 0;
        int countWWand = 0;
        int countBWand = 0;
        int countIWand = 0;
        int countGWand = 0;
        for (String item : inventory){
            switch (item){
                case "wooden sword":
                    countWSword++;
                    break;
                case "bronze sword":
                    countBSword++;
                    break;
                case "iron sword":
                    countISword++;
                    break;
                case "gold sword":
                    countGSword++;
                    break;
                case "wooden bow":
                    countWBow++;
                    break;
                case "bronze bow":
                    countBBow++;
                    break;
                case "iron bow":
                    countIBow++;
                    break;
                case "gold bow":
                    countGBow++;
                    break;
                case "wooden wand":
                    countWWand++;
                    break;
                case "bronze wand":
                    countBWand++;
                    break;
                case "iron wand":
                    countIWand++;
                    break;
                case "gold wand":
                    countGWand++;
                    break;
            }
        }
        if (countWSword >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("wooden sword");
            }
            inventory.add("bronze sword");
        }
        if (countBSword >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("bronze sword");
            }
            inventory.add("iron sword");
        }
        if (countISword >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("iron sword");
            }
            inventory.add("gold sword");
        }
        if (countGSword >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("gold sword");
            }
            inventory.add("legendary sword");
            System.out.println("Congrats! You just made legendary sword!");
        }
        if (countWBow >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("wooden bow");
            }
            inventory.add("bronze bow");
        }
        if (countBBow >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("bronze bow");
            }
            inventory.add("iron bow");
        }
        if (countIBow >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("iron bow");
            }
            inventory.add("gold bow");
        }
        if (countGBow >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("gold bow");
            }
            inventory.add("legendary bow");
            System.out.println("Congrats! You just made legendary bow!");
        }
        if (countWWand >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("wooden wand");
            }
            inventory.add("bronze wand");
        }
        if (countBWand >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("bronze wand");
            }
            inventory.add("iron wand");
        }
        if (countIWand >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("iron wand");
            }
            inventory.add("gold wand");
        }
        if (countGWand >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("gold wand");
            }
            inventory.add("legendary wand");
            System.out.println("Congrats! You just made legendary wand!");
        }
    }

    public void fight(Character enemy, Scanner scanner){
        int variable = 0;
        long maxHealth = this.getHealth();
        String choiceToFight = null;
        while (this.getHealth() != 0 | enemy.getHealth() != 0){
            //TODO fight mechanics with abilities and skills
            if (variable % 2 == 0) {
                System.out.println("Choose: \n a: defense     b: attack");
                choiceToFight = scanner.nextLine();
                switch (choiceToFight) {
                    case "b":
                        if (this.getRace().equals("Mage")) {
                            enemy.setHealth(enemy.getHealth() + enemy.getDefense() - this.getIntelligence());
                        } else {
                            enemy.setHealth(enemy.getHealth() + enemy.getDefense() - this.getStrength());
                        }
                        break;
                    case "a":
                        if (this.getDefense() + 2 < enemy.getStrength() | this.getDefense() + 2 < enemy.getIntelligence()) {
                            if (enemy.getRace().equals("Mage")) {
                                this.setHealth(this.getHealth() + this.getDefense() + 2 - enemy.getIntelligence());
                            } else {
                                this.setHealth(this.getHealth() + this.getDefense() + 2 - enemy.getStrength());
                            }
                        } else if (this.getDefense() < enemy.getStrength() | this.getDefense() < enemy.getIntelligence()) {
                            if (enemy.getRace().equals("Mage")) {
                                this.setHealth(this.getHealth() + this.getDefense() + 1 - enemy.getIntelligence());
                            } else {
                                this.setHealth(this.getHealth() + this.getDefense() + 1 - enemy.getStrength());
                            }
                        }
                        break;
                    default:
                        System.out.println("You typed wrong, automatically turning your move to enemy...");
                        break;
                }
            }

            if (enemy.getHealth() <= 0 & this.getHealth() > 0){
                System.out.println("You won the fight!");
                if (enemy.getLevel() > this.getLevel()){
                    System.out.println("You earned" + 50*enemy.getLevel() + "xp");
                    this.setXp(this.getXp() + 50 * enemy.getLevel());
                } else if (enemy.getLevel() == this.getLevel()){
                    System.out.println("You earned" + 25*enemy.getLevel() + "xp");
                    this.setXp(this.getXp() + 25 * enemy.getLevel());
                } else if (enemy.getLevel() < this.getLevel()){
                    System.out.println("You earned" + 10*enemy.getLevel() + "xp");
                    this.setXp(this.getXp() + 10 * enemy.getLevel());
                }
                this.openCase();
            } else if (this.getHealth() <= 0 & enemy.getHealth() > 0){
                System.out.println("You lost!");
                System.out.println("You earned " + 5*enemy.getLevel() + "xp");
                this.setXp(this.getXp() + 5 * enemy.getLevel());
            }

            if (variable % 2 != 0){
                if (enemy.getRace().equals("Mage")){
                    this.setHealth(this.getHealth() + this.getDefense() - enemy.getIntelligence());
                } else {
                    this.setHealth(this.getHealth() + this.getDefense() - enemy.getStrength());
                }
            } else if (!choiceToFight.equals("a") & !choiceToFight.equals("b")){
                if (enemy.getRace().equals("Mage")){
                    this.setHealth(this.getHealth() + this.getDefense() - (enemy.getIntelligence() + 1));
                } else {
                    this.setHealth(this.getHealth() + this.getDefense() - (enemy.getStrength() + 1));
                }
            }

            variable++;
        }
        this.setHealth(maxHealth);
    }

    public void openCase(){
        Random random = new Random();
        int randChance = random.nextInt(100) + 1;
        if (this.getLevel() <= 15){
            if (randChance <= 85) this.getInventory().add("wooden sword");                      //85%
            if (randChance > 85 & randChance <= 92) this.getInventory().add("bronze sword");    //7%
            if (randChance > 92 & randChance <= 97) this.getInventory().add("iron sword");      //5%
            if (randChance > 97) this.getInventory().add("gold sword");                         //3%
        }
    }

    public void training(){
        if (trainingCompleted){
            System.out.println("You can train only once per level!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        //train only once every level, not every day to become imbalance
        System.out.println("What do you want to train? Remember, you can only train once every level!");
        System.out.println("a:  Strength     b:  defense    c: intelligence(only if you are mage)");
        String trainingType = scanner.nextLine();
        while (!trainingType.equals("a") & !trainingType.equals("b") & !trainingType.equals("c")){
            System.out.println("Invalid input! Try again:");
            System.out.println("What do you want to train? Remember, you can only train once every level!");
            System.out.println("a:  Strength     b:  defense    c: intelligence");
            trainingType = scanner.nextLine();
        }
        switch (trainingType){
            case "a":
                //if you want to train strength(if you are not mage)
                if (!this.getRace().equals("Mage")) {
                    this.setStrength(this.getStrength() + 1);
                    this.setXp(this.getXp() + (this.getLevel())*20);
                    break;
                } else {
                    System.out.println("You cannot train strength as mage!");
                    training();
                }
            case "b":
                //if tou want to train defense
                this.setDefense(this.getDefense() + 1);
                this.setXp(this.getXp() + (this.getLevel())*20);
                break;
            case "c":
                //if you want to train intelligence
                if (this.getRace().equals("Mage")) {
                    this.setIntelligence(this.getIntelligence() + 1);
                    this.setXp(this.getXp() + (this.getLevel())*20);
                    break;
                } else {
                    System.out.println("You can only train intelligence as mage");
                    training();
                }
        }
        trainingCompleted = true;
    }

    public void lvlUp(long xpToNext){
        this.setLevel(this.getLevel() + 1);
        this.setXp(this.getXp() - this.getXpToNextLvl());
        this.setHealth(this.getHealth() + 2);
        this.setDefense(this.getDefense() + 1);
        if (!this.getRace().equals("Mage")){
            this.setStrength(this.getStrength() + 1);
        } else {
            this.setIntelligence(this.getIntelligence() + 1);
        }
        this.setXpToNextLvl(this.getXpToNextLvl()+200);
        trainingCompleted = false;
        if (xpToNext != 0){
            System.out.println("Level up! You are now at " + this.getLevel() + " level" + "\nNow you have +1 Strength, +5 health and +1 defense");
        }
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
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

    public void setIntelligence(long intelligence) {
        this.intelligence = intelligence;
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

    public void setName(String name){
        this.name = name;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setXpToNextLvl(long xpToNextLvl) {
        this.xpToNextLvl = xpToNextLvl;
    }

    public long getStrength() {
        return strength;
    }

    public long getIntelligence() {
        return intelligence;
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

    public String getClassification() {
        return classification;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public long getXpToNextLvl() {
        return xpToNextLvl;
    }
}
