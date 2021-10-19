package com.company;
import java.util.List;
public class HorrorMovies extends Movie
{

    public HorrorMovies(String title,int year, double rating,List<String>cast,double basePrice, MovieDiscout discout)
    {
        super(title,year,rating,cast,basePrice,discout);
    }

   /* @Override//poate lipsi
    public double calculatePrice(){
        return super.getBasePrice()*0.9;
    }*/
    //not usefull after alterration
}
