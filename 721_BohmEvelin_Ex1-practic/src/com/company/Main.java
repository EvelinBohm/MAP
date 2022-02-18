package com.company;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Repository repository=new Repository();
       repository.readFile();
        //System.out.println(repository.getList());
       Controller conroller=new Controller(repository);
       List<Kunden> sortierteListe=conroller.sortedKunden(repository.getList());
       repository.writeToFile("C:\\Users\\Bohm Evelin\\Desktop\\TestPractic_Ex1\\src\\com\\company\\ kundensortiert.txt",sortierteListe," ");
      //  System.out.println( conroller.sortedKunden(repository.getList()));
        repository.writeToFileString("C:\\Users\\Bohm Evelin\\Desktop\\TestPractic_Ex1\\src\\com\\company\\ statistic.txt",conroller.filteredList(repository.getList())," ");

        System.out.println(conroller.filteredList(repository.getList()));
    }
}
