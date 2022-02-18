package com.company.repository;

import com.company.model.Produkte;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryProdukt implements ICrudRepository<Produkte> {
private final List<Produkte> produkteList;

    public RepositoryProdukt() {
        this.produkteList =new ArrayList<>();
    }

    @Override
    public Produkte findOne(Integer id)  {

        return this.produkteList
                .stream()
                .filter(produkte -> Objects.equals(produkte.getId(), id))
                .findAny()
                .orElse(null);
    }

    @Override
    public Iterable<Produkte> findAll() {
        return produkteList;
    }

    @Override
    public Produkte save(Produkte entity) {
        if(this.findOne(entity.getId())==null) {
            this.produkteList.add(entity);
            return null;
        }
        return entity;
    }

    @Override
    public Produkte delete(Integer id) {
        Produkte produkte=this.findOne(id);
        if(produkte!=null){
            this.produkteList.remove(produkte);
        }
        return produkte;
    }

    @Override
    public Produkte update(Produkte entity) {
        if (this.produkteList.stream().filter(produkte -> Objects.equals(produkte.getId(), entity.getId())).map(produkte -> entity).anyMatch(produkte -> true)) {
            return null;
        }
        return entity;
    }
}
