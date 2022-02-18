package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }


public List<Kunden> sortedKunden(List<Kunden>listKunden)

{
    return  listKunden
            .stream()
            .sorted(Comparator.comparing(Kunden::getAnzahlMitarbeiter).reversed())
            .collect(Collectors.toList());



}

    public List<String> filteredList(List<Kunden>list)
    {


        Map<String,List<Kunden>> filteredMap=list
                .stream()
                .sorted(Comparator.comparing(Kunden::getEinkommenMitarbeiter))
                .collect(Collectors.groupingBy(Kunden::getOrt))
                ;

        List<String> filteredListOfOrts = new ArrayList<String>(filteredMap.keySet());
        return filteredListOfOrts;

    }



}
