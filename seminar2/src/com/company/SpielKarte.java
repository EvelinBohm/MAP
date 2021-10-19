package com.company;

public class SpielKarte {
    private int wert;
    private String farbe;


    public SpielKarte(int wert,String farbe)
    {
        this.wert=wert;
        this.farbe=farbe;
    }

    public int getWert() {
        return wert;
    }

    @Override
    public String toString() {
        return "SpielKarte{" +
                "wert=" + wert +
                ", farbe='" + farbe + '\'' +
                '}';
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }



}

