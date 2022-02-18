package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Repository {


    private List<Kunden> list=new ArrayList<>();

    public void readFile(){
        try {
            File myObj = new File("C:\\Users\\Bohm Evelin\\Desktop\\TestPractic_Ex1\\src\\com\\company\\ kundendaten.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String>words= List.of(data.split(","));
                Integer id=Integer.parseInt(words.get(0));
                String unternehmensname=words.get(1);
                Unternehmensgröße unternehmensgröße=Unternehmensgröße.valueOf(words.get(2));
                Integer anzahl=Integer.parseInt(words.get(3));
                double einkommen=Double.parseDouble(words.get(4));
                String ort=words.get(5);
                Kunden kunden=new Kunden(id,unternehmensname,unternehmensgröße,anzahl,einkommen,ort);
                list.add(kunden);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void writeToFile(String fileName, List<Kunden> liste, String character) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        for (Kunden kunden : liste) {
            String line = kunden.getId() + character + kunden.getUnternehmensname() + character +
                    kunden.getUnternehmensgröße() + character + kunden.getAnzahlMitarbeiter()+character+ kunden.getEinkommenMitarbeiter()+character+kunden.getOrt();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    public void writeToFileString(String fileName, List<String> liste, String character) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        for (String ort : liste) {
            String line =  ort+character;
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }





    public List<Kunden>getList(){return list;}

}
