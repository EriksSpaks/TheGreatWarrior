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
import java.util.Scanner;

public class Game {

    public void saveGame(Character character, World world, String path) throws FileNotFoundException {
        //Create path to your saves!
        final String PATH = path + character.getName() + ".json";

        JSONObject jsonObject = new JSONObject();
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

        return inventory;
    }

    public Character loadCharacter(String path, Scanner scanner) throws IOException, ParseException, NullPointerException{
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
        World world = new World();

        Object obj = new JSONParser().parse(new FileReader(path + name.get(characterNumber-1)));
        JSONObject jsonObject = (JSONObject) obj;

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
