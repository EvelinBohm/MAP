package com.company.controller;

import com.company.model.Bestellungen;
import com.company.model.Produkte;
import com.company.repository.RepositoryBestellungen;
import com.company.repository.RepositoryProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Produkte produkte1,produkte2,produkte3;
    Bestellungen bestellungen1,bestellungen2;
    RepositoryProdukt repositoryProdukt;
    RepositoryBestellungen repositoryBestellungen;
    Controller controller;
    @BeforeEach
    void setUp() {

         produkte1= new Produkte(1,"p1",2);
         produkte2= new Produkte(2,"p2",3);
         produkte3= new Produkte(3,"p3",4);
         List<Produkte> produkteList=new ArrayList<>();
         List<Produkte> produkteList1=new ArrayList<>();
         produkteList1.add(produkte1);
         produkteList1.add(produkte2);
         produkteList.add(produkte3);
         repositoryProdukt=new RepositoryProdukt();
         repositoryProdukt.save(produkte1);
         repositoryProdukt.save(produkte2);
         repositoryProdukt.save(produkte3);


         bestellungen1=new Bestellungen(4,"addr1",produkteList);
         bestellungen2=new Bestellungen(5,"addr2",produkteList1);
         repositoryBestellungen=new RepositoryBestellungen();
         repositoryBestellungen.save(bestellungen1);
         repositoryBestellungen.save(bestellungen2);
         controller=new Controller(repositoryBestellungen,repositoryProdukt);


    }

    @Test
    void sortBestellungen() {

       List<Bestellungen> sortedList=controller.sortBestellungen();

    }

    @Test
    void filterBestellungen() {

        List<Bestellungen>filteredList=controller.filterBestellungen(produkte1);
        assertEquals(filteredList.size(),1);
        //nur bestellung2 hat produkt2
        assertEquals(filteredList.get(0),bestellungen2);

    }
}