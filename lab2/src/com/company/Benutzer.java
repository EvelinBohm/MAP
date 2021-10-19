
package com.company;

import java.util.ArrayList;

/**
 *
 * The Benutzer Klasse implementiert 3 Methoden, die die Gesammtzeit bzw Durchschnittszeit von
 * gewissen Sportarten berechnet und auch ausdrueckt.
 *
 *  @author Evelin Bohm
 *  @version 1.0
 *  @since 2021-10-18
 */
public class Benutzer {
    private String vorName;
    private String nachName;
    private ArrayList<Sport> sport;


    /**
     *
     * @param vorName der Vorname des Benutzers
     * @param nachName der Nachname des Benutzers
     * @param sport eine Liste von Sportarten
     */
    public Benutzer(String vorName,String nachName, ArrayList<Sport> sport)
    {
        this.vorName=vorName;
        this.nachName=nachName;
        this.sport=sport;
    }

    /**
     * Die Methode berechnet die Gesammtzeit, die mit Sport treiben verbracht wurde
     * @return double,die Methode gibt die Gesammtzeit,die mit Sport verbracht wurde
     * aus der Liste des Benutzers zurueck
     */
    public double kalkuliereZeit()
    {
         double sum=0;
         for ( Sport sports:this.sport)
         {
             sum+=sports.kalkuliereZeit();

         }
         return sum;
    }

    /**
     * Die Methode berechnet die Durchschnittszeit, die mit Sport treiben verbracht wurde
     * @return double,die Methode gibt die Durchschnittszeit,die mit Sport verbracht wurde
     * aus der Liste des Benutzers zurueck
     */
    public double kalkuliereDurchschnittsZeit()
    {
        double sum=0;
        for ( Sport sports:this.sport)
            sum+= sports.kalkuliereZeit();

        return sum/this.sport.size();
    }

    /**
     * Die Methode berechnet die Gesammtzeit, die mit einer gewissen Sportart verbracht wurde
     * @param sport_art enthaelt die Sportart anhand welcher die Gesammtzeit berechnet wird
     * @return double,die Methode gibt die Gesammtzeit von der input sport_art zurucek
     */
    public double kalkuliereZeit(Sport sport_art)
    {
        double sumZeit=0;
        for (Sport sportArt:this.sport)
            if (sport_art.kalkuliereZeit()==sportArt.kalkuliereZeit())
                sumZeit+=sportArt.kalkuliereZeit();
       return sumZeit;
    }

    //getters
    public String getVorName() {
        return vorName;
    }
    public String getNachName() {
        return nachName;
    }
    public ArrayList<Sport> getSport() {
        return sport;
    }

    //setters
    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public void setSport(ArrayList<Sport> sport) {
        this.sport = sport;
    }

}
