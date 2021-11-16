package com.company.repository;
import java.util.List;

/**
 *
 * @param <T> a class from the model package
 * InMemoryRepository implements the ICrudRepository
 *
 * @author Bohm Evelin
 * @version 30.10.2021
 * @since 1.0
 */
public abstract class InMemoryRepository<T> implements ICrudRepository<T> {
    protected List<T> repositoryList;

    public InMemoryRepository(List<T> repositoryList) {

        this.repositoryList = repositoryList;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<T> findAll() {

        return this.repositoryList;
    }



}