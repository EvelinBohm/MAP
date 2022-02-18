package com.company;

public class Kunden {

    private Integer id;
    private String unternehmensname;
    private Unternehmensgröße unternehmensgröße;
    private Integer anzahlMitarbeiter;
    private  double einkommenMitarbeiter;
    private String ort;


    public Kunden(Integer id, String unternehmensname, Unternehmensgröße unternehmensgröße, Integer anzahlMitarbeiter, double einkommenMitarbeiter, String ort) {
        this.id = id;
        this.unternehmensname = unternehmensname;
        this.unternehmensgröße = unternehmensgröße;
        this.anzahlMitarbeiter = anzahlMitarbeiter;
        this.einkommenMitarbeiter = einkommenMitarbeiter;
        this.ort = ort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnternehmensname() {
        return unternehmensname;
    }

    public void setUnternehmensname(String unternehmensname) {
        this.unternehmensname = unternehmensname;
    }

    public Unternehmensgröße getUnternehmensgröße() {
        return unternehmensgröße;
    }

    public void setUnternehmensgröße(Unternehmensgröße unternehmensgröße) {
        this.unternehmensgröße = unternehmensgröße;
    }

    public Integer getAnzahlMitarbeiter() {
        return anzahlMitarbeiter;
    }

    public void setAnzahlMitarbeiter(Integer anzahlMitarbeiter) {
        this.anzahlMitarbeiter = anzahlMitarbeiter;
    }

    public double getEinkommenMitarbeiter() {
        return einkommenMitarbeiter;
    }

    public void setEinkommenMitarbeiter(double einkommenMitarbeiter) {
        this.einkommenMitarbeiter = einkommenMitarbeiter;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return "Kunden{" +
                "id=" + id +
                ", unternehmensname='" + unternehmensname + '\'' +
                ", unternehmensgröße=" + unternehmensgröße +
                ", anzahlMitarbeiter=" + anzahlMitarbeiter +
                ", einkommenMitarbeiter=" + einkommenMitarbeiter +
                ", ort='" + ort + '\'' +
                '}';
    }
}
