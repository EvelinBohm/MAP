package com.company.model;

import jdk.jshell.spi.SPIResolutionException;

import java.util.List;

public class Bestellungen {
    private Integer bestellnr;
    private String adresse;
    private List<Produkte> produkteList;

    public Bestellungen(Integer bestellnr, String adresse, List<Produkte> produkteList) {
        this.bestellnr = bestellnr;
        this.adresse = adresse;
        this.produkteList = produkteList;
    }

    public Integer getBestellnr() {
        return bestellnr;
    }

    public void setBestellnr(Integer bestellnr) {
        this.bestellnr = bestellnr;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Produkte> getProdukteList() {
        return produkteList;
    }

    public void setProdukteList(List<Produkte> produkteList) {
        this.produkteList = produkteList;
    }

    @Override
    public String toString() {
        return "Bestellungen{" +
                "bestellnr=" + bestellnr +
                ", adresse='" + adresse + '\'' +
                ", produkteList=" + produkteList +
                '}';
    }
}
