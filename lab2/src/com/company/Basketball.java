package com.company;

/**
 *Die Klasse Basketball implementiert die kalkuliereZeit Methode,die von der Klasse Mannschaftsport vererbt wurde
 */

public  class Basketball extends  Mannschaftsport{

    /**
     * Die Methode ueberschreibt die kalkuliereZeit Methode
     * @return double,gibt die Zeit die fuer Basketball noetig ist, zurueck
     */
    @Override
    public  double kalkuliereZeit() {
        return 55;
    }
}
