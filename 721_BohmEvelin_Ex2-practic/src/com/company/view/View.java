package com.company.view;




import com.company.controller.Controller;
import com.company.model.Bestellungen;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class View {
   private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    };

    public void run() {
        Scanner scanner = new Scanner(System.in);

        loop:
        while (true) {
            System.out.println("""
                     
                     0. Exit Program 
                     1. Show all Bestellungen 
                     2. Show all Produkte
                     3. Add a Bestellung
                     4. Add an Produkt
                     5. Remove a Produkt 
                     6. Remove an Bestellung
                     7. Update a Bestellung
                     8. Update an Produkt
                     9. Sort Bestellungen 
                     10. Filter Bestellungen
                    """);
            System.out.println("Enter input: ");
            int variant = scanner.nextInt();
            System.out.println("You've entered: " + variant);
            switch (variant) {
                case 0:
                    break loop;
                case 1:
                    showBestellungen();
                    break;
                case 2:
                    showProdukte();
                    break;
                case 3:
                    addBestellungen();
                    break;
                case 4:
                    addProdukte();
                    break;
                case 5:
                    deleteAProdukte();
                    break;
                case 6:
                    deleteBestellungen();
                    break;
                case 7:
                    updateAProdukte();
                    break;
                case 8:
                    updateBestellungen();
                    break;
                case 9:
                    sortAlleBestellungen();
                    break;
                case 10:
                    filtereBestellungen();
                    break;
            }

        }
    }

    private void showBestellungen() {
        System.out.println(controller.findAllBestellungen());
    }
    private void showProdukte() {
        System.out.println(controller.findAllProdukte());
    }

    private void addBestellungen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You will need to write a few things that represent Bestellungen attributes.");
        System.out.println("Enter Bestellungen nr:");
        try {
            int id = scanner.nextInt();
            System.out.println("Enter Bestellungen adress:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String adress = scanner.nextLine();



            Bestellungen bestellungen=new Bestellungen(id,adress,new ArrayList<>());
            controller.saveBestellungen(bestellungen);
        } catch (Exception e) {
            throw e;
        }
    }

    private void deleteBestellungen() {
        showBestellungen();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Bestellungen nr:");
        Integer id = scanner.nextInt();
        controller.deleteBestellungen(id);
    }

    private void updateBestellungen() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Bestellungen nr:");
            int id = scanner.nextInt();
            Bestellungen foundBestellung=controller.findOneBestellungen(id);
            System.out.println("Enter Bestellungen adress:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String adress = scanner.nextLine();

             foundBestellung.setAdresse(adress);
             controller.updateBestellungen(foundBestellung);
        } catch (Exception e) {
            throw e;
        }
    }




}
