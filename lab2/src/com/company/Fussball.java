package com.company;

/**
 *Die Klasse Fussball implementiert die kalkuliereZeit Methode,die von der Klasse Mannschaftsport vererbt wurde
 */
public  class Fussball extends Mannschaftsport {

     /*
     *ueberschreibt die kalkuliereZeit Methode
     * @return double,gibt die Zeit die fuer Fussball noetig ist, zurueck
     */
    @Override
    public double kalkuliereZeit() {
        return 65;
    }
}
