package com.company;
import java.util.List;

public class ScifiMovies extends Movie{
  /*  @Override//poate lipsi
    public double calculatePrice(){

        return super.getBasePrice();
    }*/
    public ScifiMovies(String title,int year, double rating,List<String>cast,double basePrice,MovieDiscout discount)
    {
        super(title,year,rating,cast,basePrice,discount);
    }
}
