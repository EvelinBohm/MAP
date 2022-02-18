package com.company.model;

public class Produkte {
    private Integer id;
    private String name;
    private Integer preis;

    public Produkte(Integer id, String name, Integer preis) {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPreis() {
        return preis;
    }

    public void setPreis(Integer preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Produkte{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pris=" + preis +
                '}';
    }
}
