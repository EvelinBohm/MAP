package com.company.Repository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
/**
 * @param <T> , a class from Model package
 * InFileRepo class implementing ICrudRepository
 * reading,storing and updating information about the list of T objects
 *
 * @author Bohm Evelin
 * @version 16.11.2021
 */
public abstract class InFileRepo<T> implements ICrudRepository<T>{
    protected List<T> repoList;

    public InFileRepo(List<T> repoList) throws FileNotFoundException {
        this.repoList = (List<T>) readDataFromFile();
    }
    public abstract List<T> readDataFromFile()throws FileNotFoundException;
    public abstract  void writeToFile() throws IOException;
}
