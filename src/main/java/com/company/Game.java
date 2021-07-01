package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    public void saveGame(Character character, World world, String path) throws FileNotFoundException {
        //Create path to your saves!
        final String PATH = path + character.getName() + ".json";

        JSONObject jsonObject = new JSONObject();
        HashMap<String, Boolean> areItemsAdded = new HashMap<>();
        areItemsAdded.put("first wooden sword added", character.isFirstWoodenSwordAdded());
        areItemsAdded.put("first bronze sword added", character.isFirstBronzeSwordAdded());
        areItemsAdded.put("first iron sword added", character.isFirstIronSwordAdded());
        areItemsAdded.put("first gold sword added", character.isFirstGoldSwordAdded());
        areItemsAdded.put("first legendary sword added", character.isFirstLegendarySwordAdded());
        areItemsAdded.put("first wooden bow added", character.isFirstWoodenBowAdded());
        areItemsAdded.put("first bronze bow added", character.isFirstBronzeBowAdded());
        areItemsAdded.put("first iron bow added", character.isFirstIronBowAdded());
        areItemsAdded.put("first gold bow added", character.isFirstGoldBowAdded());
        areItemsAdded.put("first legendary bow added", character.isFirstLegendaryBowAdded());
        areItemsAdded.put("first wooden wand added", character.isFirstWoodenWandAdded());
        areItemsAdded.put("first bronze wand added", character.isFirstBronzeWandAdded());
        areItemsAdded.put("first iron wand added", character.isFirstIronWandAdded());
        areItemsAdded.put("first gold wand added", character.isFirstGoldWandAdded());
        areItemsAdded.put("first legendary wand added", character.isFirstLegendaryWandAdded());
        areItemsAdded.put("first wooden shield added", character.isFirstWoodenShieldAdded());
        areItemsAdded.put("first bronze shield added", character.isFirstBronzeShieldAdded());
        areItemsAdded.put("first iron shield added", character.isFirstIronShieldAdded());
        areItemsAdded.put("first gold shield added", character.isFirstGoldShieldAdded());
        areItemsAdded.put("first legendary shield added", character.isFirstLegendaryShieldAdded());

        HashMap<String, Long> itemStrengthAndDefense = new HashMap<>();
        itemStrengthAndDefense.put("wooden sword strength", character.getWoodenSwordStrength());
        itemStrengthAndDefense.put("bronze sword strength", character.getBronzeSwordStrength());
        itemStrengthAndDefense.put("iron sword strength", character.getIronSwordStrength());
        itemStrengthAndDefense.put("gold sword strength", character.getGoldSwordStrength());
        itemStrengthAndDefense.put("legendary sword strength", character.getLegendarySwordStrength());
        itemStrengthAndDefense.put("wooden bow strength", character.getWoodenBowStrength());
        itemStrengthAndDefense.put("bronze bow strength", character.getBronzeBowStrength());
        itemStrengthAndDefense.put("iron bow strength", character.getIronBowStrength());
        itemStrengthAndDefense.put("gold bow strength", character.getGoldBowStrength());
        itemStrengthAndDefense.put("legendary bow strength", character.getLegendaryBowStrength());
        itemStrengthAndDefense.put("wooden wand intelligence", character.getWoodenWandIntelligence());
        itemStrengthAndDefense.put("bronze wand intelligence", character.getBronzeWandIntelligence());
        itemStrengthAndDefense.put("iron wand intelligence", character.getIronWandIntelligence());
        itemStrengthAndDefense.put("gold wand intelligence", character.getGoldWandIntelligence());
        itemStrengthAndDefense.put("legendary wand intelligence", character.getLegendaryWandIntelligence());
        itemStrengthAndDefense.put("wooden shield defense", character.getWoodenShieldDefense());
        itemStrengthAndDefense.put("bronze shield defense", character.getBronzeShieldDefense());
        itemStrengthAndDefense.put("iron shield defense", character.getIronShieldDefense());
        itemStrengthAndDefense.put("gold shield defense", character.getGoldShieldDefense());
        itemStrengthAndDefense.put("legendary shield defense", character.getLegendaryShieldDefense());

        jsonObject.put("strength", character.getStrength());
        jsonObject.put("intelligence", character.getIntelligence());
        jsonObject.put("defense", character.getDefense());
        jsonObject.put("vitality", character.getVitality());
        jsonObject.put("wisdom", character.getWisdom());
        jsonObject.put("health", character.getHealth());
        jsonObject.put("mana", character.getMana());
        jsonObject.put("level", character.getLevel());
        jsonObject.put("xp", character.getXp());
        jsonObject.put("world day", world.getDay());
        jsonObject.put("name", character.getName());
        jsonObject.put("race", character.getRace());
        jsonObject.put("class", character.getClassification());
        jsonObject.put("xp to next level", character.getXpToNextLvl());
        jsonObject.put("are items added first time", areItemsAdded);
        jsonObject.put("item strength and defense", itemStrengthAndDefense);

        PrintWriter pw = new PrintWriter(PATH);
        pw.write(jsonObject.toJSONString());
        pw.flush();
        pw.close();

    }

    public void saveInventory(String path, Character character) throws ParserConfigurationException, FileNotFoundException, TransformerException {
        // TODO: 6/21/2021 save armor
        final String PATH = path + character.getName() + ".xml";

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("inventory");
        doc.appendChild(rootElement);

        Element itemSword = doc.createElement("weapon");

        Element itemBow = doc.createElement("weapon");

        Element itemWand = doc.createElement("weapon");

        Element itemShield = doc.createElement("defense");

        for (int i = 0; i< character.getInventory().size(); i++){
            if (character.getInventory().get(i).contains("sword")){
                rootElement.appendChild(itemSword);
            }
            if (character.getInventory().get(i).contains("bow")){
                rootElement.appendChild(itemBow);
            }
            if (character.getInventory().get(i).contains("wand")){
                rootElement.appendChild(itemWand);
            }
            if (character.getInventory().get(i).contains("shield")){
                rootElement.appendChild(itemShield);
            }
        }

        while (character.getInventory().size() != 0){
            if (character.getInventory().get(0).contains("sword")){
                switch (character.getInventory().get(0)){
                    case "wooden sword":
                        Element sword = doc.createElement("sword");
                        sword.setTextContent("wooden sword");
                        itemSword.appendChild(sword);
                        character.getInventory().remove(0);
                        break;
                    case "bronze sword":
                        sword = doc.createElement("sword");
                        sword.setTextContent("bronze sword");
                        itemSword.appendChild(sword);
                        character.getInventory().remove(0);
                        break;
                    case "iron sword":
                        sword = doc.createElement("sword");
                        sword.setTextContent("iron sword");
                        itemSword.appendChild(sword);
                        character.getInventory().remove(0);
                        break;
                    case "gold sword":
                        sword = doc.createElement("sword");
                        sword.setTextContent("gold sword");
                        itemSword.appendChild(sword);
                        character.getInventory().remove(0);
                        break;
                    case "legendary sword":
                        sword = doc.createElement("sword");
                        sword.setTextContent("legendary sword");
                        itemSword.appendChild(sword);
                        character.getInventory().remove(0);
                        break;
                }
            } else if (character.getInventory().get(0).contains("bow")){
                switch (character.getInventory().get(0)){
                    case "wooden bow":
                        Element bow = doc.createElement("bow");
                        bow.setTextContent("wooden bow");
                        itemBow.appendChild(bow);
                        character.getInventory().remove(0);
                        break;
                    case "bronze bow":
                        bow = doc.createElement("bow");
                        bow.setTextContent("bronze bow");
                        itemBow.appendChild(bow);
                        character.getInventory().remove(0);
                        break;
                    case "iron bow":
                        bow = doc.createElement("bow");
                        bow.setTextContent("iron bow");
                        itemBow.appendChild(bow);
                        character.getInventory().remove(0);
                        break;
                    case "gold bow":
                        bow = doc.createElement("bow");
                        bow.setTextContent("gold bow");
                        itemBow.appendChild(bow);
                        character.getInventory().remove(0);
                        break;
                    case "legendary bow":
                        bow = doc.createElement("bow");
                        bow.setTextContent("legendary bow");
                        itemBow.appendChild(bow);
                        character.getInventory().remove(0);
                        break;
                }
            } else if (character.getInventory().get(0).contains("wand")){
                switch (character.getInventory().get(0)){
                    case "wooden wand":
                        Element wand = doc.createElement("wand");
                        wand.setTextContent("wooden wand");
                        itemWand.appendChild(wand);
                        character.getInventory().remove(0);
                        break;
                    case "bronze wand":
                        wand = doc.createElement("wand");
                        wand.setTextContent("bronze wand");
                        itemWand.appendChild(wand);
                        character.getInventory().remove(0);
                        break;
                    case "iron wand":
                        wand = doc.createElement("wand");
                        wand.setTextContent("iron wand");
                        itemWand.appendChild(wand);
                        character.getInventory().remove(0);
                        break;
                    case "gold wand":
                        wand = doc.createElement("wand");
                        wand.setTextContent("gold wand");
                        itemWand.appendChild(wand);
                        character.getInventory().remove(0);
                        break;
                    case "legendary wand":
                        wand = doc.createElement("wand");
                        wand.setTextContent("legendary wand");
                        itemWand.appendChild(wand);
                        character.getInventory().remove(0);
                        break;
                }
            } else if (character.getInventory().get(0).contains("shield")){
                switch (character.getInventory().get(0)){
                    case "wooden shield":
                        Element shield = doc.createElement("shield");
                        shield.setTextContent("wooden shield");
                        itemShield.appendChild(shield);
                        character.getInventory().remove(0);
                        break;
                    case "bronze wand":
                        shield = doc.createElement("shield");
                        shield.setTextContent("bronze shield");
                        itemShield.appendChild(shield);
                        character.getInventory().remove(0);
                        break;
                    case "iron shield":
                        shield = doc.createElement("shield");
                        shield.setTextContent("iron shield");
                        itemShield.appendChild(shield);
                        character.getInventory().remove(0);
                        break;
                    case "gold shield":
                        shield = doc.createElement("shield");
                        shield.setTextContent("gold shield");
                        itemShield.appendChild(shield);
                        character.getInventory().remove(0);
                        break;
                    case "legendary shield":
                        shield = doc.createElement("shield");
                        shield.setTextContent("legendary shield");
                        itemShield.appendChild(shield);
                        character.getInventory().remove(0);
                        break;
                }
            }
        }

        FileOutputStream outputStream = new FileOutputStream(PATH);
        writeXml(doc, outputStream);
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    public ArrayList<String> loadInventory(String path, Character character) throws IOException, SAXException, ParserConfigurationException {
        // TODO: 6/21/2021 load armor
        final String PATH = path + character.getName() + ".xml";
        ArrayList<String> inventory = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(PATH));

        doc.getDocumentElement().normalize();
        NodeList items = doc.getElementsByTagName("weapon");

        for (int i = 0; i < items.getLength(); i++) {
            Node item = items.item(i);
            NodeList equipment = item.getChildNodes();
            String temp = equipment.item(1).getTextContent();
            for (int j = 0; j < equipment.getLength(); j++) {
                if (temp.contains("sword") & equipment.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element sword = (Element) equipment.item(j);
                    String swordName = sword.getTextContent();
                    inventory.add(swordName);
                }
                if (temp.contains("bow") & equipment.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element bow = (Element) equipment.item(j);
                    String bowName = bow.getTextContent();
                    inventory.add(bowName);
                }
                if (temp.contains("wand") & equipment.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element wand = (Element) equipment.item(j);
                    String wandName = wand.getTextContent();
                    inventory.add(wandName);
                }
            }
        }
        items = doc.getElementsByTagName("defense");
        for (int i = 0; i < items.getLength(); i++) {
            Node item = items.item(i);
            NodeList equipment = item.getChildNodes();
            String temp = equipment.item(1).getTextContent();
            for (int j = 0; j < equipment.getLength(); j++) {
                if (temp.contains("shield") & equipment.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element shield = (Element) equipment.item(j);
                    String shieldName = shield.getTextContent();
                    inventory.add(shieldName);
                }
            }
        }
        return inventory;
    }

    public Character loadCharacter(String path, Scanner scanner, World world) throws IOException, ParseException, NullPointerException{
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
        scanner.nextLine();
        while (characterNumber > name.size()){
            System.out.println("Invalid input, try again!");
            printSaveFiles(name);
            characterNumber = Integer.parseInt(scanner.nextLine());
        }

        Character character = new Warrior("", name.get(characterNumber-1).replace(".json", ""), "Warrior");

        Object obj = new JSONParser().parse(new FileReader(path + name.get(characterNumber-1)));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject areItemsAdded = (JSONObject) jsonObject.get("are items added first time");
        JSONObject itemStrengthAndDefense = (JSONObject) jsonObject.get("item strength and defense");

        character.setLevel((long) jsonObject.get("level"));
        character.setIntelligence((long) jsonObject.get("intelligence"));
        character.setDefense((long) jsonObject.get("defense"));
        character.setHealth((long) jsonObject.get("health"));
        character.setMana((long) jsonObject.get("mana"));
        character.setStrength((long) jsonObject.get("strength"));
        character.setVitality((long) jsonObject.get("vitality"));
        character.setWisdom((long) jsonObject.get("wisdom"));
        character.setXp((long) jsonObject.get("xp"));
        character.setRace((String) jsonObject.get("race"));
        character.setName((String) jsonObject.get("name"));
        character.setClassification((String) jsonObject.get("class"));
        character.setXpToNextLvl((long) jsonObject.get("xp to next level"));

        character.setFirstWoodenSwordAdded((Boolean) areItemsAdded.get("first wooden sword added"));
        character.setFirstBronzeSwordAdded((Boolean) areItemsAdded.get("first bronze sword added"));
        character.setFirstIronSwordAdded((Boolean) areItemsAdded.get("first iron sword added"));
        character.setFirstGoldSwordAdded((Boolean) areItemsAdded.get("first gold sword added"));
        character.setFirstLegendarySwordAdded((Boolean) areItemsAdded.get("first legendary sword added"));
        character.setFirstWoodenBowAdded((Boolean) areItemsAdded.get("first wooden bow added"));
        character.setFirstBronzeBowAdded((Boolean) areItemsAdded.get("first bronze bow added"));
        character.setFirstIronBowAdded((Boolean) areItemsAdded.get("first iron bow added"));
        character.setFirstGoldBowAdded((Boolean) areItemsAdded.get("first gold bow added"));
        character.setFirstLegendaryBowAdded((Boolean) areItemsAdded.get("first legendary bow added"));
        character.setFirstWoodenWandAdded((Boolean) areItemsAdded.get("first wooden wand added"));
        character.setFirstBronzeWandAdded((Boolean) areItemsAdded.get("first bronze wand added"));
        character.setFirstIronWandAdded((Boolean) areItemsAdded.get("first iron wand added"));
        character.setFirstGoldWandAdded((Boolean) areItemsAdded.get("first gold wand added"));
        character.setFirstLegendaryWandAdded((Boolean) areItemsAdded.get("first legendary wand added"));
        character.setFirstWoodenShieldAdded((Boolean) areItemsAdded.get("first wooden shield added"));
        character.setFirstBronzeShieldAdded((Boolean) areItemsAdded.get("first bronze shield added"));
        character.setFirstIronShieldAdded((Boolean) areItemsAdded.get("first iron shield added"));
        character.setFirstGoldShieldAdded((Boolean) areItemsAdded.get("first gold shield added"));
        character.setFirstLegendaryShieldAdded((Boolean) areItemsAdded.get("first legendary shield added"));

        character.setWoodenSwordStrength((Long) itemStrengthAndDefense.get("wooden sword strength"));
        character.setBronzeSwordStrength((Long) itemStrengthAndDefense.get("bronze sword strength"));
        character.setIronSwordStrength((Long) itemStrengthAndDefense.get("iron sword strength"));
        character.setGoldSwordStrength((Long) itemStrengthAndDefense.get("gold sword strength"));
        character.setLegendarySwordStrength((Long) itemStrengthAndDefense.get("legendary sword strength"));
        character.setWoodenBowStrength((Long) itemStrengthAndDefense.get("wooden bow strength"));
        character.setBronzeBowStrength((Long) itemStrengthAndDefense.get("bronze bow strength"));
        character.setIronBowStrength((Long) itemStrengthAndDefense.get("iron bow strength"));
        character.setGoldBowStrength((Long) itemStrengthAndDefense.get("gold bow strength"));
        character.setLegendaryBowStrength((Long) itemStrengthAndDefense.get("legendary bow strength"));
        character.setWoodenWandIntelligence((Long) itemStrengthAndDefense.get("wooden wand intelligence"));
        character.setBronzeWandIntelligence((Long) itemStrengthAndDefense.get("bronze wand intelligence"));
        character.setIronWandIntelligence((Long) itemStrengthAndDefense.get("iron wand intelligence"));
        character.setGoldWandIntelligence((Long) itemStrengthAndDefense.get("gold wand intelligence"));
        character.setLegendaryWandIntelligence((Long) itemStrengthAndDefense.get("legendary wand intelligence"));
        character.setWoodenShieldDefense((Long) itemStrengthAndDefense.get("wooden shield defense"));
        character.setBronzeShieldDefense((Long) itemStrengthAndDefense.get("bronze shield defense"));
        character.setIronShieldDefense((Long) itemStrengthAndDefense.get("iron shield defense"));
        character.setGoldShieldDefense((Long) itemStrengthAndDefense.get("gold shield defense"));
        character.setLegendaryShieldDefense((Long) itemStrengthAndDefense.get("legendary shield defense"));

        world.setDay((long) jsonObject.get("world day"));

        return character;
    }

    public static void printSaveFiles(ArrayList<String> arrayList){
        System.out.println("Choose your character: ");
        for (int i = 0; i < arrayList.size(); i++){
            System.out.println(i+1 + ": " + arrayList.get(i).replace(".json", ""));
        }
    }
}
