package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main{

    //Path to your saves
    static String SAVESPATH;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Character character;
        Game game = new Game();
        System.out.println("Welcome to my rpg project!");
        System.out.println("First of all, create path to your saves:");
        SAVESPATH = scanner.nextLine();
        File savesFile = new File(SAVESPATH);
        //Checking if path to saves is valid
        while (!savesFile.isDirectory()){
            System.out.println("Directory path is not valid. Try again:");
            System.out.println("Create path to your saves:");
            SAVESPATH = scanner.nextLine();
            savesFile = new File(SAVESPATH);
        }
        //adding "\" to the end of path if the last char of path is not "\" (D:\Saves -> D:\Saves\)
        if (SAVESPATH.charAt(SAVESPATH.length()-1) != '\\'){
            SAVESPATH += "\\";
        }

        System.out.println("Start a new game or load your character from a save:");
        System.out.println("a: load character      b: new character");
        String loadOrNewGame = scanner.nextLine();
        while(true){
            if (loadOrNewGame.equals("a")){
                if (!areSavesEmpty(SAVESPATH)){
                    character = game.loadCharacter(SAVESPATH, scanner);
                    character.setInventory(game.loadInventory(SAVESPATH, character));
                    break;
                }
            }
            if (loadOrNewGame.equals("b")){
                character = newCharacter(scanner);
                switch (character.getRace()){
                    case "Orc":
                        character.setStrength(character.getStrength() + 3);
                        character.setVitality(character.getVitality() + 2);
                        character.setDefense(character.getDefense() - 1);
                        break;
                    case "Elf":
                        character.setIntelligence(character.getIntelligence() + 1);
                        character.setWisdom(character.getWisdom() + 1);
                        character.setVitality(character.getVitality() + 1);
                        break;
                    case "Human":
                        character.setStrength(character.getStrength()+1);
                        character.setVitality(character.getVitality() + 1);
                        break;
                }
                break;
            }
            System.out.println("Invalid input, try again!");
            System.out.println("Start a new game or load your character from a save:");
            System.out.println("a: load character      b: new game");
            loadOrNewGame = scanner.nextLine();
        }

        //TODO game logics and game AND rewrite code below normally
        World world = new World();
        boolean gameGoes = true;
        String save;
        String quitOrContinue;
        Random rand = new Random();
        while (gameGoes){

            //TODO game
            System.out.println("Day " + world.getDay());
            Character enemy = makeEnemy(character, rand);
            System.out.println("What do you want to do?");
            System.out.println("a: training     b: fight with enemy(" + enemy.getLevel() + " lvl)");
            String thisDayAction = scanner.nextLine();
            switch (thisDayAction){
                case "a":
                    character.training();
                    break;
                case "b":
                    character.fight(enemy, scanner);
                    break;
                default:
                    System.out.println("Wrong action. Skipping the day...");
            }
            if (character.getXp() >= character.getXpToNextLvl()){
                character.lvlUp(character.getXpToNextLvl());
            }

            System.out.println("Do you want to save game?(every input except 'a' will automatically continue the game without saving it)");
            System.out.println("a: yes     b: no");
            save = scanner.nextLine();

            if (save.equals("a")){
                game.saveGame(character, world, SAVESPATH);
                game.saveInventory(SAVESPATH, character);
            }

            System.out.println("Do you want to continue or quit the game?(every input except 'a', will quit the game)");
            System.out.println("a: continue     b: quit");
            quitOrContinue = scanner.nextLine();
            if (!quitOrContinue.equals("a")){
                gameGoes = false;
            }

            world.setDay(world.getDay() + 1);
        }
    }

    public static Character makeEnemy(Character player, Random rand){
        Character enemy = null;
        int randomClass = rand.nextInt(3);
        int randomRace = rand.nextInt(3);
        switch (randomClass){
            case 0:
                switch (randomRace){
                    case 0:
                        enemy = new Mage("Orc", "enemy", "Mage");
                        break;
                    case 1:
                        enemy = new Mage("Elf", "enemy", "Mage");
                        break;
                    case 2:
                        enemy = new Mage("Human", "enemy", "Mage");
                        break;
                }
                break;
            case 1:
                switch (randomRace){
                    case 0:
                        enemy = new Warrior("Orc", "enemy", "Warrior");
                        break;
                    case 1:
                        enemy = new Warrior("Elf", "enemy", "Warrior");
                        break;
                    case 2:
                        enemy = new Warrior("Human", "enemy", "Warrior");
                        break;
                }
                break;
            case 2:
                switch (randomRace){
                    case 0:
                        enemy = new Ranger("Orc", "enemy", "Ranger");
                        break;
                    case 1:
                        enemy = new Ranger("Elf", "enemy", "Ranger");
                        break;
                    case 2:
                        enemy = new Ranger("Human", "enemy", "Ranger");
                        break;
                }
                break;
        }
        int randomLevel = rand.nextInt(3);
        randomLevel += player.getLevel()-1;
        for (int i = 0; i < randomLevel; i++){
            enemy.lvlUp(0);
        }
        return enemy;
    }

    public static boolean areSavesEmpty(String path){
        String[] pathNames;
        File f = new File(path);
        FilenameFilter filter = (f1, name) -> name.endsWith(".json");

        pathNames = f.list(filter);
        ArrayList<String> name = new ArrayList<>();

        for (String pathname : pathNames) {
            name.add(pathname);
        }
        if (name.isEmpty()){
            System.out.println("There are no saves in saves directory");
            return true;
        }
        return false;
    }

    public static Character newCharacter(Scanner scanner) throws Exception{
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Choose your characters race: ");
        System.out.println("a: Human    b: Orc     c: Elf");
        String race = scanner.nextLine();
        while (!race.equals("a") & !race.equals("b") & !race.equals("c")){
            System.out.println("Invalid input. Try again: ");
            System.out.println("Choose your characters race: ");
            System.out.println("a: Human    b: Orc     c: Elf");
            race = scanner.nextLine();
            if (race.equals("a") | race.equals("b") | race.equals("c")){
                break;
            }
        }
        switch(race){
            case "a":
                race = "Human";
                break;
            case "b":
                race = "Orc";
                break;
            case "c":
                race = "Elf";
                break;
        }
        System.out.println("Choose your characters class: ");
        System.out.println("a: Warrior    b: Ranger     c: Mage");
        String classification = scanner.nextLine();
        while (!classification.equals("a") & !classification.equals("b") & !classification.equals("c")){
            System.out.println("Invalid input. Try again: ");
            System.out.println("Choose your characters class: ");
            System.out.println("a: Warrior    b: Ranger     c: Mage");
            classification = scanner.nextLine();
        }

        switch(classification){
            case "a":
                return new Warrior(race, name, "Warrior");
            case "b":
                return new Ranger(race, name, "Ranger");
            case "c":
                return new Mage(race, name, "Mage");
            default:
                throw new Exception();
        }
    }
}
