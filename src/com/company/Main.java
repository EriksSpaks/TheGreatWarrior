package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Path to your saves
    final static String PATH = "C:\\Users\\eriks\\IdeaProjects\\TheGreatWarrior\\Saves\\";

    public static void main(String[] args) throws Exception {
        Character character = null;
        System.out.println("Start a new game or load your character from a save:");
        System.out.println("a: load character      b: new game");
        //TODO Start game before new character
        Scanner scanner = new Scanner(System.in);
        String loadOrNewGame = scanner.nextLine();
        while(true){
            if (loadOrNewGame.equals("a")){
                loadCharacter(PATH, scanner);
                break;
            }
            if (loadOrNewGame.equals("b")){
                character = newCharacter(scanner);
                break;
            }
            System.out.println("Invalid input, try again!");
            System.out.println("Start a new game or load your character from a save:");
            System.out.println("a: load character      b: new game");
        }

        //TODO game logics and game AND rewrite code below normally
        World world = new World();
        Boolean gameGoes = true;
        String save = "save game";
        if(gameGoes == true & loadOrNewGame.equals("b")){
            //TODO game
            if (save == "save game"){
                System.out.println("Yes");
                saveGame(character, world, PATH);
            }
            world.setDay(world.getDay() + 1);
        }
    }

    public static void saveGame(Character character, World world, String path) throws FileNotFoundException {
        //Create path to your saves!
        final String PATH = path + character.getName() + ".json";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strength", character.getStrength());
        jsonObject.put("intelligence", character.getIntelligence());
        jsonObject.put("dexterity", character.getDexterity());
        jsonObject.put("defense", character.getDefense());
        jsonObject.put("vitality", character.getVitality());
        jsonObject.put("wisdom", character.getWisdom());
        jsonObject.put("health", character.getHealth());
        jsonObject.put("mana", character.getMana());
        jsonObject.put("level", character.getLevel());
        jsonObject.put("xp", character.getXp());
        jsonObject.put("world day", world.getDay());

        PrintWriter pw = new PrintWriter(PATH);
        pw.write(jsonObject.toJSONString());
        pw.flush();
        pw.close();
    }

    public static void loadCharacter(String path, Scanner scanner) throws IOException, ParseException, NullPointerException {
        String[] pathNames;
        File f = new File(path);
        FilenameFilter filter = (f1, name) -> name.endsWith(".json");

        pathNames = f.list(filter);
        ArrayList<String> name = new ArrayList<>();

        for (String pathname : pathNames) {
            name.add(pathname);
        }
        printSaveFiles(name);
        int characterNumber = scanner.nextInt();
        while (characterNumber > name.size()){
            System.out.println("Invalid input, try again!");
            printSaveFiles(name);
            characterNumber = Integer.parseInt(scanner.nextLine());
        }

        Character character = new Warrior("Human", name.get(characterNumber-1).replace(".json", ""));
        World world = new World();

        Object obj = new JSONParser().parse(new FileReader(path + name.get(characterNumber-1)));
        JSONObject jsonObject = (JSONObject) obj;

        character.setLevel((long) jsonObject.get("level"));
        character.setIntelligence((long) jsonObject.get("intelligence"));
        character.setDefense((long) jsonObject.get("defense"));
        character.setDexterity((long) jsonObject.get("dexterity"));
        character.setHealth((long) jsonObject.get("health"));
        character.setMana((long) jsonObject.get("mana"));
        character.setStrength((long) jsonObject.get("strength"));
        character.setVitality((long) jsonObject.get("vitality"));
        character.setWisdom((long) jsonObject.get("wisdom"));
        character.setXp((long) jsonObject.get("xp"));
        world.setDay((long) jsonObject.get("world day"));
        //TODO character.setRace and setName
    }

    public static void printSaveFiles(ArrayList<String> arrayList){
        System.out.println("Choose your character: ");
        for (int i = 0; i < arrayList.size(); i++){
            System.out.println(i+1 + ": " + arrayList.get(i).replace(".json", ""));
        }
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
            race = scanner.nextLine();
        }

        switch(classification){
            case "a":
                return new Warrior(race, name);
            case "b":
                return new Ranger(race, name);
            case "c":
                return new Mage(race, name);
            default:
                throw new Exception();
        }
    }
}
