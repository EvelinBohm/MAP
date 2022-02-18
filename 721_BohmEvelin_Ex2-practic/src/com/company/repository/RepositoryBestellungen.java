package com.company.repository;

import com.company.model.Bestellungen;
import com.company.model.Bestellungen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryBestellungen implements ICrudRepository<Bestellungen>{
    private final List<Bestellungen> bestellungenList;

    public RepositoryBestellungen() {
        this.bestellungenList =new ArrayList<>();
    }

    @Override
    public Bestellungen findOne(Integer id)  {

        return this.bestellungenList
                .stream()
                .filter(bestellungen -> Objects.equals(bestellungen.getBestellnr(), id))
                .findAny()
                .orElse(null);
    }

    @Override
    public Iterable<Bestellungen> findAll() {
        return bestellungenList;
    }

    @Override
    public Bestellungen save(Bestellungen entity) {
        if(this.findOne(entity.getBestellnr())==null) {
            this.bestellungenList.add(entity);
            return null;
        }
        return entity;
    }

    @Override
    public Bestellungen delete(Integer id) {
        Bestellungen bestellungen=this.findOne(id);
        if(bestellungen!=null){
            this.bestellungenList.remove(bestellungen);
        }
        return bestellungen;
    }

    @Override
    public Bestellungen update(Bestellungen entity) {
        if (this.bestellungenList.stream().filter(bestellungen -> bestellungen.getBestellnr() == entity.getBestellnr()).map(bestellungen -> entity).anyMatch(bestellungen -> true)) {
            return null;
        }
        return entity;
    }

}
