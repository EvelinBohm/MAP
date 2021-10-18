package com.company;

import com.sun.source.tree.Scope;

import java.io.NotActiveException;
import java.util.Arrays;

public class Main
{

    public static void main(String[] args) {
        Uni universitat = new Uni();
        int[] GradeList = {40,32, 21, 99, 90, 84, 71,77, 88, 86};
        System.out.println("\nAufgabe1:");
        System.out.println("Notenliste:"+ Arrays.toString(GradeList));
        int[] result = universitat.nichtAusreichend(GradeList);
        System.out.println("Aufgabe1.1(nicht ausreichende Noten):"+Arrays.toString(result));
        System.out.println("Aufgabe1.2(Durchschnitt):"+universitat.avg(GradeList));
        int[] result2 = universitat.abgerundet(GradeList);
        System.out.println("Aufgabe1.3(Abgerundete Noten):"+Arrays.toString(result2));
        System.out.println("Aufgabe1.4(Maximale abgerundete Note):"+universitat.maxNote(GradeList));


        System.out.println("---------------------------------------------------\nAufgabe2:");
        Aufgabe2 aufgabe2 = new Aufgabe2();
        int[] ListEvenNr = new int[]{2, 2,4, 12,90,36,28};
        System.out.println("Zahlenliste:"+ Arrays.toString(ListEvenNr));
        System.out.println("Aufgabe2.1(Max Zahl):"+aufgabe2.maxZahl(ListEvenNr));
        System.out.println("Aufgabe2.2(Min Zahl):"+aufgabe2.minZahl(ListEvenNr));
        System.out.println("Aufgabe2.3(Maximale Summe von n-1 Zahlen):"+aufgabe2.maxSum(ListEvenNr));
        System.out.println("Aufgabe2.4(Minimale Summe von n-1 Zahlen):"+aufgabe2.minSum(ListEvenNr));


        System.out.println("---------------------------------------------------\nAufgabe3:");
        Aufgabe3 aufgabe3 = new Aufgabe3();
        int[] FirstArrayNr = new int[]{1,3,0,0,0,0,0,0,0};
        int[] SecondArrayNr = new int[]{8,7,0,0,0,0,0,0,0};
        int[] FirstArrayNrDif = new int[]{8,3,0,0,0,0,0,0,0};
        int[] SecondArrayNrDif = new int[]{5,4,0,0,0,0,0,0,0};
        int[] FirstArrayNrDif2 = new int[]{8,6,0,0,0,0,0,0,0};
        int[] SecondArrayNrDif2 = new int[]{9,4,0,0,0,0,0,0,0};
        int[] MultiplicationArray = new int[]{2,3,6,0,0,0,0,0,0};
        int[] DivisionArray = new int[]{2,3,6,0,0,0,0,0,0};
        System.out.println("Summe:"+ Arrays.toString(aufgabe3.sumArrays(FirstArrayNr,SecondArrayNr)));
        System.out.println("Dif:"+ Arrays.toString(aufgabe3.subArray(FirstArrayNrDif,SecondArrayNrDif)));
        System.out.println("Dif:"+ Arrays.toString(aufgabe3.subArray(FirstArrayNrDif2,SecondArrayNrDif2)));
        System.out.println("Mul:"+ Arrays.toString(aufgabe3.multipicationArray(MultiplicationArray,5)));
        System.out.println("Div:"+ Arrays.toString(aufgabe3.divArray(DivisionArray,3)));
        aufgabe3.divArray(DivisionArray,0);


        System.out.println("---------------------------------------------------\nAufgabe4:");
        ElectronicsShop aufgabe4 = new ElectronicsShop();
        int[] Keyboard = new int[]{20,34,15,35};
        int[] USB=new int[]{20,15,40,15};
        int[] Price=new int[]{15,40,25,29,33,12,89,28};
        int[] Keyboard2 = new int[]{40,60,50};
        int[] Keyboardfail = new int[]{60};
        int[] USB2=new int[]{8,12};
        System.out.println("Tastaturliste:"+ Arrays.toString(Keyboard));
        System.out.println("USB Liste:"+ Arrays.toString(USB));
        System.out.println("Gunstigste Tastatur:"+ (aufgabe4.cheapestKeyboar(Keyboard)));
        System.out.println("Teuerstes Produkt:"+ (aufgabe4.mostExpensivProduct(Keyboard,USB)));
        System.out.println("Preis:"+ Arrays.toString(Price));
        System.out.println("Buget: 30 \nTeuerstes budget Produkt:"+(aufgabe4.budgetProduct(Price,30)));
        System.out.println("Neue TastaturListe:"+ Arrays.toString(Keyboard2));
        System.out.println("Neue ListeUSB:"+ Arrays.toString(USB2));
        int buget=60;
        System.out.println((String.format("Max Geldbetrag fur USB+Tastatur mit %d buget:"+ (aufgabe4.budgetForUSBAndKeyboard(Keyboard2,USB2 ,buget)),buget)));
        System.out.println("Neue Tastaturliste:"+ Arrays.toString(Keyboardfail));
        System.out.println((String.format("Max Geldbetrag fur USB+Tastatur mit %d buget:"+ (aufgabe4.budgetForUSBAndKeyboard(Keyboardfail,USB2 ,buget)),buget)));


    }
}



