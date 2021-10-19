package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Die Klasse liest die Daten des Benutzers und zeigt die Ergebnisse an,
 * die anhand seiner eingegeben Daten, berechnet werden konnten
 */
public class UI {

    /**
     *Die Methode liest die Daten vom Benutzer
     * @return Benutzer, gibt den Benuzter mit den gegeben Daten zurueck
     */
    public Benutzer leseUserInfo()
    {


        Scanner in=new Scanner(System.in);
        ArrayList<Sport> list=new ArrayList<>();
        System.out.println("Bitte geben sie Ihren Vornamen ein:");
        String vorName=in.nextLine();
        System.out.println("Bitte geben sie Ihren Nachnamen ein:");
        String nachName=in.nextLine();

        System.out.println("""
                          Bitte geben sie Ihre lieblings Sportarten ein:
                          b-basketball,f-fussball, h-hochsprung, H-hindernislauf, q-falls Sie fertig sind mit dem eintragen
                          """);

        String sport=in.nextLine();
        String q="q";

        Mannschaftsport basketball =new Basketball();
        Mannschaftsport fussball =new Fussball();
        Leichtathletik hindernislauf=new Hindernislauf();
        Leichtathletik hochsprung=new Hochsprung();

        //Die Sportarten werden in eine Liste eingefuegt
        while(!sport.equals(q))
        {
            if (sport.equals("f"))
                list.add(fussball);
            if (sport.equals("b"))
                list.add(basketball);
            if (sport.equals("h"))
                list.add(hochsprung);
            if (sport.equals("H"))
                list.add(hindernislauf);
            if (!sport.equals("H") && !sport.equals("h") && !sport.equals("f") && !sport.equals("b"))
                System.out.println("Bitte geben Sie gultige Daten ein");

            sport=in.nextLine();
        }

        return  new Benutzer(vorName,nachName,list);
    }


    /**
     *Die Methode zeigt die Angendungsmoglichkeiten an und ruft die
     * vom Benutzer gewaehlten Methoden auf
     */
    public void optionen(){


        Mannschaftsport basketball =new Basketball();
        Mannschaftsport fussball =new Fussball();
        Leichtathletik hindernislauf=new Hindernislauf();
        Leichtathletik hochsprung=new Hochsprung();

        Scanner in=new Scanner(System.in);

        Benutzer benutzer=leseUserInfo();
        String vorName=benutzer.getVorName();
        String nachName=benutzer.getNachName();
        System.out.println("Guten Tag,"+vorName+" "+nachName+ " was fuer Daten moechten Sie anhand ihrer Sportliste erfahren:");
        System.out.println("""
                            1.Zeige die gesammte Zeit, die ich mit Sport machen verbracht habe, an
                            2.Berechne die durchschnittliche Zeit, die ich fuers Sport machen gebraucht habe
                            3.Berechne die Zeit,die fur eine gegebene Sportart gebraucht wird
                            4. Quit
                            Waehlen Sie eine Option:
                            """);
        String option=in.nextLine();

        //Auswahl Moeglichkeiten
        while (!option.equals("4"))

        {

            if (option.equals("1"))
                System.out.println("Gesammt Zeit, die Sie mit Sport verbracht haben:"+benutzer.kalkuliereZeit());
            if (option.equals("2"))
                System.out.println("Durchschnitts Zeit, die Sie mit Sport verbracht haben:"+benutzer.kalkuliereDurchschnittsZeit());
            if (option.equals("3"))
            {
                System.out.println("""
                        Bitte geben sie die Sportart ein ueber welches Sie die gewuenschte Information erfahren wollen
                        b-basketball f-fussball h-hochsprung H-hindernislauf
                        """);
                System.out.println("Sportart:");

                String sportTyp=in.nextLine();
                if (sportTyp.equals("f"))
                    System.out.println("Gesammt Zeit, die Sie mit Fussball verbracht haben ist: "+benutzer.kalkuliereZeit(fussball));
                if (sportTyp.equals("b"))
                    System.out.println("Gesammt Zeit, die Sie mit Basketball verbracht haben ist: "+benutzer.kalkuliereZeit(basketball));
                if (sportTyp.equals("h"))
                    System.out.println("Gesammt Zeit, die Sie mit Hochsprung verbracht haben ist: "+benutzer.kalkuliereZeit(hochsprung));
                if (sportTyp.equals("H"))
                    System.out.println("Gesammt Zeit, die Sie mit hindernislauf verbracht haben ist: "+benutzer.kalkuliereZeit(hindernislauf));
                while (!sportTyp.equals("H") && !sportTyp.equals("h") && !sportTyp.equals("f") && !sportTyp.equals("b"))
                {
                    System.out.println("Bitte geben Sie einen gueltigen Eintrag ein!Falls Sie doch nicht die Funktion wollen drucken Sie q");
                    sportTyp=in.nextLine();
                    if (sportTyp.equals("q")){
                        break;
                    }

                }
            }
            if (!option.equals("1") && !option.equals("2") && !option.equals("3") )
                System.out.println("Bitte geben Sie einen gueltigen Eintrag ein!");


            System.out.println("""
                            1.Zeige die gesammte Zeit, die ich mit Sport machen verbracht habe,an
                            2.Berechne die durchschnittliche Zeit, die ich fuers Sport machen gebraucht habe
                            3.Berechne die Zeit,die fur eine gegebene Sportart gebraucht wird
                            4. Quit
                            Waehlen Sie eine Option:
                            """);
            option=in.nextLine();

        }



    }



}
