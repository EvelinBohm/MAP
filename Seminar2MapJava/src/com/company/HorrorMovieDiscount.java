package com.company;

public class HorrorMovieDiscount implements  MovieDiscout{
    @Override
    public double calculatePrice(double basePrice){
        return 0.9*basePrice;
    }

}
