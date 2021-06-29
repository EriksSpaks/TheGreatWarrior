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
    //all possible inventory items
    private final String[] invItems =
            {"wooden sword", "bronze sword", "iron sword", "gold sword",
            "wooden bow", "bronze bow", "iron bow", "gold bow",
            "wooden wand", "bronze wand", "iron wand", "gold wand",
            "wooden shield", "bronze shield", "iron shield", "gold shield"};

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

    public int[] checkInventory(int needToPrint){
        int[] itemCount = new int[16];
        int itemsToUpgrade = 0;
        Scanner scanner = new Scanner(System.in);
        for (String item : inventory){
            switch (item){
                case "wooden sword":
                    itemCount[0]++;
                    break;
                case "bronze sword":
                    itemCount[1]++;
                    break;
                case "iron sword":
                    itemCount[2]++;
                    break;
                case "gold sword":
                    itemCount[3]++;
                    break;
                case "wooden bow":
                    itemCount[4]++;
                    break;
                case "bronze bow":
                    itemCount[5]++;
                    break;
                case "iron bow":
                    itemCount[6]++;
                    break;
                case "gold bow":
                    itemCount[7]++;
                    break;
                case "wooden wand":
                    itemCount[8]++;
                    break;
                case "bronze wand":
                    itemCount[9]++;
                    break;
                case "iron wand":
                    itemCount[10]++;
                    break;
                case "gold wand":
                    itemCount[11]++;
                    break;
                case "wooden shield":
                    itemCount[12]++;
                    break;
                case "bronze shield":
                    itemCount[13]++;
                    break;
                case "iron shield":
                    itemCount[14]++;
                    break;
                case "gold shield":
                    itemCount[15]++;
                    break;
            }
        }

        System.out.println();

        if (needToPrint == 1){
            for (int i = 0; i < itemCount.length; i++) {
                if (itemCount[i] > 0){
                    System.out.println(invItems[i] + " x " + itemCount[i]);
                }
                if (itemCount[i] >= 10){
                    itemsToUpgrade++;
                }
            }
        }
        System.out.println();
        if (itemsToUpgrade > 0){
            System.out.println("You have some items to upgrade");
            System.out.println("Do you want to upgrade them?");
            System.out.println("a: yes    b: no");
            String yesOrNo = scanner.nextLine();
            if (yesOrNo.equals("a")) {
                this.itemUpgrade();
            }
        }
        System.out.println();
        return itemCount;
    }

    public void itemUpgrade(){
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
        int[] countItems = checkInventory(0);

        if (countItems[0] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("wooden sword");
            }
            inventory.add("bronze sword");
        }
        if (countItems[1] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("bronze sword");
            }
            inventory.add("iron sword");
        }
        if (countItems[2] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("iron sword");
            }
            inventory.add("gold sword");
        }
        if (countItems[3] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("gold sword");
            }
            inventory.add("legendary sword");
            System.out.println("Congrats! You just made legendary sword!");
        }
        if (countItems[4] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("wooden bow");
            }
            inventory.add("bronze bow");
        }
        if (countItems[5] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("bronze bow");
            }
            inventory.add("iron bow");
        }
        if (countItems[6] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("iron bow");
            }
            inventory.add("gold bow");
        }
        if (countItems[7] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("gold bow");
            }
            inventory.add("legendary bow");
            System.out.println("Congrats! You just made legendary bow!");
        }
        if (countItems[8] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("wooden wand");
            }
            inventory.add("bronze wand");
        }
        if (countItems[9] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("bronze wand");
            }
            inventory.add("iron wand");
        }
        if (countItems[10] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("iron wand");
            }
            inventory.add("gold wand");
        }
        if (countItems[11] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("gold wand");
            }
            inventory.add("legendary wand");
            System.out.println("Congrats! You just made legendary wand!");
        }
        if (countItems[12] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("wooden shield");
            }
            inventory.add("bronze shield");
        }
        if (countItems[13] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("bronze shield");
            }
            inventory.add("iron shield");
        }
        if (countItems[14] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("iron shield");
            }
            inventory.add("gold shield");
        }
        if (countItems[15] >= 10){
            for (int i = 0; i < 10; i++){
                inventory.remove("gold shield");
            }
            inventory.add("legendary shield");
            System.out.println("Congrats! You just made legendary shield!");
        }
    }

    public void fight(Character enemy, Scanner scanner){
        int variable = 0;
        long maxHealth = this.getHealth();
        String choiceToFight = null;
        int increasedChanceAfterDefense = 0;
        while (this.getHealth() != 0 | enemy.getHealth() != 0){
            System.out.println("Your health: " + this.getHealth() + "\nEnemy's health: " + enemy.getHealth() + "\n");
            Random random = new Random();
            Random randomEnemy = new Random();
            int critChance = random.nextInt(10) + 1;
            int enemyCritChance = randomEnemy.nextInt(10) + 1;
            //TODO fight mechanics with abilities and skills
            if (enemy.getHealth() <= 0 & this.getHealth() > 0){
                System.out.println("You won the fight!");
                if (enemy.getLevel() > this.getLevel()){
                    System.out.println("You earned " + 50*enemy.getLevel() + "xp\n");
                    this.setXp(this.getXp() + 50 * enemy.getLevel());
                } else if (enemy.getLevel() == this.getLevel()){
                    System.out.println("You earned " + 25*enemy.getLevel() + "xp\n");
                    this.setXp(this.getXp() + 25 * enemy.getLevel());
                } else if (enemy.getLevel() < this.getLevel()){
                    System.out.println("You earned " + 10*enemy.getLevel() + "xp\n");
                    this.setXp(this.getXp() + 10 * enemy.getLevel());
                }
                this.openCase();
                break;
            } else if (this.getHealth() <= 0 & enemy.getHealth() > 0){
                System.out.println("You lost!");
                System.out.println("You earned " + 5*enemy.getLevel() + "xp\n");
                this.setXp(this.getXp() + 5 * enemy.getLevel());
                break;
            }

            if (variable % 2 == 0) {
                System.out.println("Your turn!\n");
                System.out.println("Choose: \n a: defense     b: attack");
                choiceToFight = scanner.nextLine();
                switch (choiceToFight) {
                    case "b":
                        //critical damage chance 20%, but if you have defensed last turn, you have increased chance to make critical damage
                        if (critChance <= 2 + increasedChanceAfterDefense){
                            if (this.getRace().equals("Mage")) {
                                System.out.println("Critical damage!");
                                enemy.setHealth(enemy.getHealth() + enemy.getDefense() - this.getIntelligence()*2);
                                increasedChanceAfterDefense = 0;
                            } else {
                                System.out.println("Critical damage!");
                                enemy.setHealth(enemy.getHealth() + enemy.getDefense() - this.getStrength()*2);
                                increasedChanceAfterDefense = 0;
                            }
                        } else {
                            if (this.getRace().equals("Mage")) {
                                enemy.setHealth(enemy.getHealth() + enemy.getDefense() - this.getIntelligence());
                            } else {
                                enemy.setHealth(enemy.getHealth() + enemy.getDefense() - this.getStrength());
                            }
                            increasedChanceAfterDefense = 0;
                        }
                        break;
                    case "a":
                        if (this.getDefense() + 2 <= enemy.getStrength() | this.getDefense() + 2 <= enemy.getIntelligence()) {
                            if (enemy.getRace().equals("Mage")) {
                                this.setHealth(this.getHealth() + this.getDefense() + 2 - enemy.getIntelligence());
                            } else {
                                this.setHealth(this.getHealth() + this.getDefense() + 2 - enemy.getStrength());
                            }
                            if (this.getHealth() > maxHealth){
                                this.setHealth(maxHealth);
                            }
                            increasedChanceAfterDefense = 3;
                        } else if (this.getDefense() + 1 >= enemy.getStrength() | this.getDefense() + 1 >= enemy.getIntelligence()) {
                            if (enemy.getRace().equals("Mage")) {
                                this.setHealth(this.getHealth() + this.getDefense() + 1 - enemy.getIntelligence());
                            } else {
                                this.setHealth(this.getHealth() + this.getDefense() + 1 - enemy.getStrength());
                            }
                            if (this.getHealth() > maxHealth){
                                this.setHealth(maxHealth);
                            }
                            increasedChanceAfterDefense = 3;
                        }
                        break;
                    default:
                        System.out.println("You typed wrong, automatically turning your move to enemy...");
                        break;
                }
            }

            if (variable % 2 != 0){
                System.out.println("Enemy's turn!\n");
                if (enemyCritChance <= 2){
                    if (enemy.getRace().equals("Mage")) {
                        System.out.println("Critical damage from an enemy!");
                        this.setHealth(this.getHealth() + this.getDefense() - enemy.getIntelligence()*2);
                    } else {
                        System.out.println("Critical damage from an enemy!");
                        this.setHealth(this.getHealth() + this.getDefense() - enemy.getStrength()*2);
                    }
                } else {
                    if (enemy.getRace().equals("Mage")) {
                        this.setHealth(this.getHealth() + this.getDefense() - enemy.getIntelligence());
                    } else {
                        this.setHealth(this.getHealth() + this.getDefense() - enemy.getStrength());
                    }
                }
                if (this.getHealth() > maxHealth){
                    this.setHealth(maxHealth);
                }
            } else if (!choiceToFight.equals("a") & !choiceToFight.equals("b")){
                if (enemy.getRace().equals("Mage")){
                    this.setHealth(this.getHealth() + this.getDefense() - (enemy.getIntelligence() + 1));
                } else {
                    this.setHealth(this.getHealth() + this.getDefense() - (enemy.getStrength() + 1));
                }
                if (this.getHealth() > maxHealth){
                    this.setHealth(maxHealth);
                }
            }

            variable++;
        }
        this.setHealth(maxHealth);
    }

    public void openCase(){
        Random random = new Random();
        int randChance = random.nextInt(100) + 1;
        int defenceOrStrength = random.nextInt(2);
        if (defenceOrStrength == 0){
            if (this.getClassification().equals("Warrior")){
                if (this.getLevel() <= 15){
                    if (randChance <= 85) {                                                         //85%
                        System.out.println("You received wooden sword!");
                        this.getInventory().add("wooden sword");
                    }
                    if (randChance > 85 & randChance <= 92) {                                       //7%
                        System.out.println("Rare drop! You received bronze sword!");
                        this.getInventory().add("bronze sword");
                    }
                    if (randChance > 92 & randChance <= 97) {                                       //5%
                        System.out.println("Very rare drop! You received iron sword!");
                        this.getInventory().add("iron sword");
                    }
                    if (randChance > 97) {                                                          //3%
                        System.out.println("Very rare drop! You received gold sword!");
                        this.getInventory().add("gold sword");
                    }
                }
                if (this.getLevel() >= 16 & this.getLevel() <= 30){
                    if (randChance <= 50) {                                                         //50%
                        System.out.println("You received wooden sword!");
                        this.getInventory().add("wooden sword");
                    }
                    if (randChance > 50 & randChance <= 80) {                                       //30%
                        System.out.println("You received bronze sword!");
                        this.getInventory().add("bronze sword");
                    }
                    if (randChance > 80 & randChance <= 95) {                                       //15%
                        System.out.println("Rare drop! You received iron sword!");
                        this.getInventory().add("iron sword");
                    }
                    if (randChance > 95) {                                                          //5%
                        System.out.println("Very rare drop! You received gold sword!");
                        this.getInventory().add("gold sword");
                    }
                }
                if (this.getLevel() >= 31 & this.getLevel() <= 45){
                    if (randChance <= 30) {                                                         //30%
                        System.out.println("You received wooden sword!");
                        this.getInventory().add("wooden sword");
                    }
                    if (randChance > 30 & randChance <= 70) {                                       //40%
                        System.out.println("You received bronze sword!");
                        this.getInventory().add("bronze sword");
                    }
                    if (randChance > 70 & randChance <= 90) {                                       //20%
                        System.out.println("You received iron sword!");
                        this.getInventory().add("iron sword");
                    }
                    if (randChance > 90) {                                                          //10%
                        System.out.println("Rare drop! You received gold sword!");
                        this.getInventory().add("gold sword");
                    }
                }
                if (this.getLevel() >= 46 & this.getLevel() <= 60){
                    if (randChance <= 15) {                                                         //15%
                        System.out.println("You received wooden sword!");
                        this.getInventory().add("wooden sword");
                    }
                    if (randChance > 15 & randChance <= 61) {                                       //46%
                        System.out.println("You received bronze sword!");
                        this.getInventory().add("bronze sword");
                    }
                    if (randChance > 61 & randChance <= 87) {                                       //26%
                        System.out.println("You received iron sword!");
                        this.getInventory().add("iron sword");
                    }
                    if (randChance > 87) {                                                          //13%
                        System.out.println("Rare drop! You received gold sword!");
                        this.getInventory().add("gold sword");
                    }
                }
                if (this.getLevel() >= 61 & this.getLevel() <= 75){
                    if (randChance <= 5) {                                                          //5%
                        System.out.println("You received wooden sword!");
                        this.getInventory().add("wooden sword");
                    }
                    if (randChance > 5 & randChance <= 55) {                                        //50%
                        System.out.println("You received bronze sword!");
                        this.getInventory().add("bronze sword");
                    }
                    if (randChance > 55 & randChance <= 85) {                                       //30%
                        System.out.println("You received iron sword!");
                        this.getInventory().add("iron sword");
                    }
                    if (randChance > 85) {                                                          //15%
                        System.out.println("Rare drop! You received gold sword!");
                        this.getInventory().add("gold sword");
                    }
                }
                if (this.getLevel() >= 76 & this.getLevel() <= 100){
                    if (randChance <= 1) {                                                          //1%
                        System.out.println("You received wooden sword!");
                        this.getInventory().add("wooden sword");
                    }
                    if (randChance > 1 & randChance <= 36) {                                        //35%
                        System.out.println("You received bronze sword!");
                        this.getInventory().add("bronze sword");
                    }
                    if (randChance > 36 & randChance <= 71) {                                       //35%
                        System.out.println("You received iron sword!");
                        this.getInventory().add("iron sword");
                    }
                    if (randChance > 71) {                                                          //29%
                        System.out.println("You received gold sword!");
                        this.getInventory().add("gold sword");
                    }
                }
            }

            if (this.getClassification().equals("Mage")){
                if (this.getLevel() <= 15){
                    if (randChance <= 85) {                                                         //85%
                        System.out.println("You received wooden wand!");
                        this.getInventory().add("wooden wand");
                    }
                    if (randChance > 85 & randChance <= 92) {                                       //7%
                        System.out.println("Rare drop! You received bronze wand!");
                        this.getInventory().add("bronze wand");
                    }
                    if (randChance > 92 & randChance <= 97) {                                       //5%
                        System.out.println("Very rare drop! You received iron wand!");
                        this.getInventory().add("iron wand");
                    }
                    if (randChance > 97) {                                                          //3%
                        System.out.println("Very rare drop! You received gold wand!");
                        this.getInventory().add("gold wand");
                    }
                }
                if (this.getLevel() >= 16 & this.getLevel() <= 30){
                    if (randChance <= 50) {                                                         //50%
                        System.out.println("You received wooden wand!");
                        this.getInventory().add("wooden wand");
                    }
                    if (randChance > 50 & randChance <= 80) {                                       //30%
                        System.out.println("You received bronze wand!");
                        this.getInventory().add("bronze wand");
                    }
                    if (randChance > 80 & randChance <= 95) {                                       //15%
                        System.out.println("Rare drop! You received iron wand!");
                        this.getInventory().add("iron wand");
                    }
                    if (randChance > 95) {                                                          //5%
                        System.out.println("Very rare drop! You received gold wand!");
                        this.getInventory().add("gold wand");
                    }
                }
                if (this.getLevel() >= 31 & this.getLevel() <= 45){
                    if (randChance <= 30) {                                                         //30%
                        System.out.println("You received wooden wand!");
                        this.getInventory().add("wooden wand");
                    }
                    if (randChance > 30 & randChance <= 70) {                                       //40%
                        System.out.println("You received bronze wand!");
                        this.getInventory().add("bronze wand");
                    }
                    if (randChance > 70 & randChance <= 90) {                                       //20%
                        System.out.println("You received iron wand!");
                        this.getInventory().add("iron wand");
                    }
                    if (randChance > 90) {                                                          //10%
                        System.out.println("Rare drop! You received gold wand!");
                        this.getInventory().add("gold wand");
                    }
                }
                if (this.getLevel() >= 46 & this.getLevel() <= 60){
                    if (randChance <= 15) {                                                         //15%
                        System.out.println("You received wooden wand!");
                        this.getInventory().add("wooden wand");
                    }
                    if (randChance > 15 & randChance <= 61) {                                       //46%
                        System.out.println("You received bronze wand!");
                        this.getInventory().add("bronze wand");
                    }
                    if (randChance > 61 & randChance <= 87) {                                       //26%
                        System.out.println("You received iron wand!");
                        this.getInventory().add("iron wand");
                    }
                    if (randChance > 87) {                                                          //13%
                        System.out.println("Rare drop! You received gold wand!");
                        this.getInventory().add("gold wand");
                    }
                }
                if (this.getLevel() >= 61 & this.getLevel() <= 75){
                    if (randChance <= 5) {                                                          //5%
                        System.out.println("You received wooden wand!");
                        this.getInventory().add("wooden wand");
                    }
                    if (randChance > 5 & randChance <= 55) {                                        //50%
                        System.out.println("You received bronze wand!");
                        this.getInventory().add("bronze wand");
                    }
                    if (randChance > 55 & randChance <= 85) {                                       //30%
                        System.out.println("You received iron wand!");
                        this.getInventory().add("iron wand");
                    }
                    if (randChance > 85) {                                                          //15%
                        System.out.println("Rare drop! You received gold wand!");
                        this.getInventory().add("gold wand");
                    }
                }
                if (this.getLevel() >= 76 & this.getLevel() <= 100){
                    if (randChance <= 1) {                                                          //1%
                        System.out.println("You received wooden wand!");
                        this.getInventory().add("wooden wand");
                    }
                    if (randChance > 1 & randChance <= 36) {                                        //35%
                        System.out.println("You received bronze wand!");
                        this.getInventory().add("bronze wand");
                    }
                    if (randChance > 36 & randChance <= 71) {                                       //35%
                        System.out.println("You received iron wand!");
                        this.getInventory().add("iron wand");
                    }
                    if (randChance > 71) {                                                          //29%
                        System.out.println("You received gold wand!");
                        this.getInventory().add("gold wand");
                    }
                }
            }

            if (this.getClassification().equals("Ranger")){
                if (this.getLevel() <= 15){
                    if (randChance <= 85) {                                                         //85%
                        System.out.println("You received wooden bow!");
                        this.getInventory().add("wooden bow");
                    }
                    if (randChance > 85 & randChance <= 92) {                                       //7%
                        System.out.println("Rare drop! You received bronze bow!");
                        this.getInventory().add("bronze bow");
                    }
                    if (randChance > 92 & randChance <= 97) {                                       //5%
                        System.out.println("Very rare drop! You received iron bow!");
                        this.getInventory().add("iron bow");
                    }
                    if (randChance > 97) {                                                          //3%
                        System.out.println("Very rare drop! You received gold bow!");
                        this.getInventory().add("gold bow");
                    }
                }
                if (this.getLevel() >= 16 & this.getLevel() <= 30){
                    if (randChance <= 50) {                                                         //50%
                        System.out.println("You received wooden bow!");
                        this.getInventory().add("wooden bow");
                    }
                    if (randChance > 50 & randChance <= 80) {                                       //30%
                        System.out.println("You received bronze bow!");
                        this.getInventory().add("bronze bow");
                    }
                    if (randChance > 80 & randChance <= 95) {                                       //15%
                        System.out.println("Rare drop! You received iron bow!");
                        this.getInventory().add("iron bow");
                    }
                    if (randChance > 95) {                                                          //5%
                        System.out.println("Very rare drop! You received gold bow!");
                        this.getInventory().add("gold bow");
                    }
                }
                if (this.getLevel() >= 31 & this.getLevel() <= 45){
                    if (randChance <= 30) {                                                         //30%
                        System.out.println("You received wooden bow!");
                        this.getInventory().add("wooden bow");
                    }
                    if (randChance > 30 & randChance <= 70) {                                       //40%
                        System.out.println("You received bronze bow!");
                        this.getInventory().add("bronze bow");
                    }
                    if (randChance > 70 & randChance <= 90) {                                       //20%
                        System.out.println("You received iron bow!");
                        this.getInventory().add("iron bow");
                    }
                    if (randChance > 90) {                                                          //10%
                        System.out.println("Rare drop! You received gold bow!");
                        this.getInventory().add("gold bow");
                    }
                }
                if (this.getLevel() >= 46 & this.getLevel() <= 60){
                    if (randChance <= 15) {                                                         //15%
                        System.out.println("You received wooden bow!");
                        this.getInventory().add("wooden bow");
                    }
                    if (randChance > 15 & randChance <= 61) {                                       //46%
                        System.out.println("You received bronze bow!");
                        this.getInventory().add("bronze bow");
                    }
                    if (randChance > 61 & randChance <= 87) {                                       //26%
                        System.out.println("You received iron bow!");
                        this.getInventory().add("iron bow");
                    }
                    if (randChance > 87) {                                                          //13%
                        System.out.println("Rare drop! You received gold bow!");
                        this.getInventory().add("gold bow");
                    }
                }
                if (this.getLevel() >= 61 & this.getLevel() <= 75){
                    if (randChance <= 5) {                                                          //5%
                        System.out.println("You received wooden bow!");
                        this.getInventory().add("wooden bow");
                    }
                    if (randChance > 5 & randChance <= 55) {                                        //50%
                        System.out.println("You received bronze bow!");
                        this.getInventory().add("bronze bow");
                    }
                    if (randChance > 55 & randChance <= 85) {                                       //30%
                        System.out.println("You received iron bow!");
                        this.getInventory().add("iron bow");
                    }
                    if (randChance > 85) {                                                          //15%
                        System.out.println("Rare drop! You received gold bow!");
                        this.getInventory().add("gold bow");
                    }
                }
                if (this.getLevel() >= 76 & this.getLevel() <= 100){
                    if (randChance <= 1) {                                                          //1%
                        System.out.println("You received wooden bow!");
                        this.getInventory().add("wooden bow");
                    }
                    if (randChance > 1 & randChance <= 36) {                                        //35%
                        System.out.println("You received bronze bow!");
                        this.getInventory().add("bronze bow");
                    }
                    if (randChance > 36 & randChance <= 71) {                                       //35%
                        System.out.println("You received iron bow!");
                        this.getInventory().add("iron bow");
                    }
                    if (randChance > 71) {                                                          //29%
                        System.out.println("You received gold bow!");
                        this.getInventory().add("gold bow");
                    }
                }
            }
        }
        if (defenceOrStrength == 1){
            if (this.getLevel() <= 15){
                if (randChance <= 85) {                                                         //85%
                    System.out.println("You received wooden shield!");
                    this.getInventory().add("wooden shield");
                }
                if (randChance > 85 & randChance <= 92) {                                       //7%
                    System.out.println("Rare drop! You received bronze shield!");
                    this.getInventory().add("bronze shield");
                }
                if (randChance > 92 & randChance <= 97) {                                       //5%
                    System.out.println("Very rare drop! You received iron shield!");
                    this.getInventory().add("iron shield");
                }
                if (randChance > 97) {                                                          //3%
                    System.out.println("Very rare drop! You received gold shield!");
                    this.getInventory().add("gold shield");
                }
            }
            if (this.getLevel() >= 16 & this.getLevel() <= 30){
                if (randChance <= 50) {                                                         //50%
                    System.out.println("You received wooden shield!");
                    this.getInventory().add("wooden shield");
                }
                if (randChance > 50 & randChance <= 80) {                                       //30%
                    System.out.println("You received bronze shield!");
                    this.getInventory().add("bronze shield");
                }
                if (randChance > 80 & randChance <= 95) {                                       //15%
                    System.out.println("Rare drop! You received iron shield!");
                    this.getInventory().add("iron shield");
                }
                if (randChance > 95) {                                                          //5%
                    System.out.println("Very rare drop! You received gold shield!");
                    this.getInventory().add("gold shield");
                }
            }
            if (this.getLevel() >= 31 & this.getLevel() <= 45){
                if (randChance <= 30) {                                                         //30%
                    System.out.println("You received wooden shield!");
                    this.getInventory().add("wooden shield");
                }
                if (randChance > 30 & randChance <= 70) {                                       //40%
                    System.out.println("You received bronze shield!");
                    this.getInventory().add("bronze shield");
                }
                if (randChance > 70 & randChance <= 90) {                                       //20%
                    System.out.println("You received iron shield!");
                    this.getInventory().add("iron shield");
                }
                if (randChance > 90) {                                                          //10%
                    System.out.println("Rare drop! You received gold shield!");
                    this.getInventory().add("gold shield");
                }
            }
            if (this.getLevel() >= 46 & this.getLevel() <= 60){
                if (randChance <= 15) {                                                         //15%
                    System.out.println("You received wooden shield!");
                    this.getInventory().add("wooden shield");
                }
                if (randChance > 15 & randChance <= 61) {                                       //46%
                    System.out.println("You received bronze shield!");
                    this.getInventory().add("bronze shield");
                }
                if (randChance > 61 & randChance <= 87) {                                       //26%
                    System.out.println("You received iron shield!");
                    this.getInventory().add("iron shield");
                }
                if (randChance > 87) {                                                          //13%
                    System.out.println("Rare drop! You received gold shield!");
                    this.getInventory().add("gold shield");
                }
            }
            if (this.getLevel() >= 61 & this.getLevel() <= 75){
                if (randChance <= 5) {                                                          //5%
                    System.out.println("You received wooden shield!");
                    this.getInventory().add("wooden shield");
                }
                if (randChance > 5 & randChance <= 55) {                                        //50%
                    System.out.println("You received bronze shield!");
                    this.getInventory().add("bronze shield");
                }
                if (randChance > 55 & randChance <= 85) {                                       //30%
                    System.out.println("You received iron shield!");
                    this.getInventory().add("iron shield");
                }
                if (randChance > 85) {                                                          //15%
                    System.out.println("Rare drop! You received gold shield!");
                    this.getInventory().add("gold shield");
                }
            }
            if (this.getLevel() >= 76 & this.getLevel() <= 100){
                if (randChance <= 1) {                                                          //1%
                    System.out.println("You received wooden shield!");
                    this.getInventory().add("wooden shield");
                }
                if (randChance > 1 & randChance <= 36) {                                        //35%
                    System.out.println("You received bronze shield!");
                    this.getInventory().add("bronze shield");
                }
                if (randChance > 36 & randChance <= 71) {                                       //35%
                    System.out.println("You received iron shield!");
                    this.getInventory().add("iron shield");
                }
                if (randChance > 71) {                                                          //29%
                    System.out.println("You received gold shield!");
                    this.getInventory().add("gold shield");
                }
            }
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
            System.out.println("Level up! You are now at " + this.getLevel() + " level " + "\nNow you have +1 Strength(Intelligence), +2 health and +1 defense");
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
