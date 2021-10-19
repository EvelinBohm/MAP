package com.company;
import java.util.List;
public class ComedyMovies extends Movie{

    public ComedyMovies(String title,int year, double rating,List<String>cast,double basePrice,MovieDiscout discount)
    {
        super(title,year,rating,cast,basePrice,discount);
    }
// in loc de baseprice se trimite filmul??
   /* @Override//poate lipsi
    public double calculatePrice()
    {
        if (super.getCast().contains("Adam Sandler"))
         return 0.5*super.getBasePrice();
        else
            return super.getBasePrice();
    }*/
}
