package com.company;
import java.util.List;

public abstract class Movie {

    private String title;
    private  int year;
    private double rating;
    private List<String> cast;
    private double basePrice;
    private MovieDiscout discout;


    public Movie(String title,int year, double rating,List<String>cast,double basePrice, MovieDiscout discount)
    {
        this.title=title;
        this.year=year;
        this.rating=rating;
        this.cast=cast;
        this.basePrice=basePrice;
        this.discout=discount;
    }

    public double calculatePrice(){
        return discout.calculatePrice(basePrice);
    }

    public double getBasePrice() {
        return basePrice;
    }
    public List<String> getCast() {
        return cast;
    }
}
