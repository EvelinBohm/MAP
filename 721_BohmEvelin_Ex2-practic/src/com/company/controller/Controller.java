package com.company.controller;

import com.company.model.Bestellungen;
import com.company.model.Produkte;
import com.company.repository.RepositoryBestellungen;
import com.company.repository.RepositoryProdukt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private RepositoryBestellungen repositoryBestellungen;
    private RepositoryProdukt repositoryProdukt;

    public Controller(RepositoryBestellungen repositoryBestellungen, RepositoryProdukt repositoryProdukt) {
        this.repositoryBestellungen = repositoryBestellungen;
        this.repositoryProdukt = repositoryProdukt;
    }

    private int sumOfList(List<Produkte> list) {
        return list.stream().mapToInt(Produkte::getPreis).sum();
    }


    public List<Bestellungen>sortBestellungen()
    {
        List<Bestellungen>bestellungenList=(List<Bestellungen>)repositoryBestellungen.findAll();

        return bestellungenList.stream()
                .sorted((list1, list2) -> {
                    int list1Sum = list1.getProdukteList().stream().mapToInt(Produkte::getPreis).sum();
                    int list2Sum = list2.getProdukteList().stream().mapToInt(Produkte::getPreis).sum();
                    return list1Sum - list2Sum;
                }).collect(Collectors.toList());

    }

    public List<Bestellungen>filterBestellungen(Produkte filteredProdukte)
    {
       List<Bestellungen>filteredList=new ArrayList<>();
        for (Bestellungen bestellungen:(List<Bestellungen>)repositoryBestellungen.findAll())
        {
            for (Produkte produkte:bestellungen.getProdukteList()){
                if (filteredProdukte.equals(produkte)){
                    filteredList.add(bestellungen);
                }
        }

        }


        return filteredList;
    }

    public Produkte findOneProdukte(Integer id) {
        return repositoryProdukt.findOne(id);
    }

    public List<Produkte> findAllProdukte() {
        return (List<Produkte>)repositoryProdukt .findAll();

    }

    public Produkte saveProdukte(Produkte entity) {
        return repositoryProdukt.save(entity);
    }

    public Produkte deleteProdukte( Integer id) {
        return repositoryProdukt.delete(id);
    }
    public Produkte updateProdukte(Produkte entity) {
        return repositoryProdukt.update(entity);
    }


    public Bestellungen findOneBestellungen(Integer id) {
        return repositoryBestellungen.findOne(id);
    }


    public List<Bestellungen> findAllBestellungen() {
        return( List<Bestellungen>) repositoryBestellungen.findAll();
    }


    public Bestellungen saveBestellungen(Bestellungen entity) {
        return repositoryBestellungen.save(entity);
    }


    public Bestellungen deleteBestellungen(Integer id) {
        return repositoryBestellungen.delete(id);
    }


    public Bestellungen updateBestellungen(Bestellungen entity) {
        return repositoryBestellungen.update(entity);
    }


    
}
