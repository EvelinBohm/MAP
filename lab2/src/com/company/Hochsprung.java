package com.company;

/**
 *Die Klasse Hochsprung implementiert die kalkuliereZeit Methode,die von der Klasse Leichtathletik vererbt wurde
 */
public  class Hochsprung extends  Leichtathletik{

    /**
     * Die Methode ueberschreibt die kalkuliereZeit Methode
     * @return double,gibt die Zeit die fuer Hochsprung noetig ist, zurueck
     */
    @Override
    public double kalkuliereZeit(){
        return 20;
    }
}
