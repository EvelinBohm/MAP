package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse fuehrt Teste fuer die Methoden aus
 */
class BenutzerTest {


    /**
     * Die Methode testet ob die Gesammtzeit,die furs Sport treiben verbraucht wurde
     *gut berechnet wurde
     */
    @Test
    void kalkuliereZeit() {
        ArrayList<Sport> lieblingssport=new ArrayList<>();

        Mannschaftsport basketballTest =new Basketball();
        Mannschaftsport fussballTest =new Fussball();
        Mannschaftsport basketballTest2 =new Basketball();
        Leichtathletik hindernislaufTest=new Hindernislauf();
        Leichtathletik hochsprungTest=new Hochsprung();

        lieblingssport.add(basketballTest);
        lieblingssport.add(fussballTest);
        lieblingssport.add(hindernislaufTest);
        lieblingssport.add(basketballTest2);
        lieblingssport.add(hochsprungTest);

        Benutzer benutzer=new Benutzer("TestVN","TestNN",lieblingssport);

        assertEquals(benutzer.kalkuliereZeit(),225);

    }


    /**
     * Die Methode testet ob die Durchschnittszeit(Minuten),die furs Sport treiben verbraucht wurde
     *gut berechnet wurde
     */
    @Test
    void kalkuliereDurchschnittsZeit() {


        ArrayList<Sport> lieblingssport=new ArrayList<>();

        Mannschaftsport basketballTest =new Basketball();
        Mannschaftsport fussballTest =new Fussball();
        Mannschaftsport basketballTest2 =new Basketball();
        Leichtathletik hindernislaufTest=new Hindernislauf();
        Leichtathletik hochsprungTest=new Hochsprung();

        lieblingssport.add(basketballTest);
        lieblingssport.add(fussballTest);
        lieblingssport.add(hindernislaufTest);
        lieblingssport.add(basketballTest2);
        lieblingssport.add(hochsprungTest);

        Benutzer benutzer=new Benutzer("TestVN","TestNN",lieblingssport);

        assertEquals(benutzer.kalkuliereDurchschnittsZeit(),45);



    }


    /**
     *  Die Methode testet ob die Gesammtzeit,die man die fuer eine gegebenen Sportarten braucht
     *gut berechnet wurde
     */
    @Test
    void testKalkuliereZeit() {

        ArrayList<Sport> lieblingssport=new ArrayList<>();

        Mannschaftsport basketballTest =new Basketball();
        Mannschaftsport fussballTest =new Fussball();
        Mannschaftsport basketballTest2 =new Basketball();
        Leichtathletik hindernislaufTest=new Hindernislauf();
        Leichtathletik hochsprungTest=new Hochsprung();

        lieblingssport.add(basketballTest);
        lieblingssport.add(fussballTest);
        lieblingssport.add(hindernislaufTest);
        lieblingssport.add(basketballTest2);
        lieblingssport.add(hochsprungTest);

        Benutzer benutzer=new Benutzer("TestVN","TestNN",lieblingssport);

        Basketball b=new Basketball();

        assertEquals(benutzer.kalkuliereZeit(b),110);
    }
}