package com.company;

/**
 *Die Klasse Hindernislauf implementiert die kalkuliereZeit Methode,die von der Klasse Leichtathletik vererbt wurde
 */
public  class Hindernislauf extends  Leichtathletik{
    /*
     * ueberschreibt die kalkuliereZeit Methode
     * @return double,gibt die Zeit die fuer Hindernislauf noetig ist, zurueck
     */
    @Override
    public double kalkuliereZeit() {
        return 30;
    }
}
