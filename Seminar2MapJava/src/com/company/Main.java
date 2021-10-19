package com.company;
import  java.util.Arrays;
import java.util.Date;

public class Main {
// we used the strategy pattern
    public static void main(String[] args) {
        ComedyMovies comedy1=new ComedyMovies("titlu1",1999,3,Arrays.asList("Adam Sandler","Nume1","Nume2"),100,new HorrorMovieDiscount());
        System.out.println(comedy1.calculatePrice());

        User user1=new User("Fname","Lname");
        OrderLine orderl=new OrderLine(comedy1,10);
        Order o=new Order(new Date(),Arrays.asList(orderl));
        user1.addOrder(o);
        System.out.println(user1.calculateTotalPriceForAllOrders());
        System.out.println(user1.calculateTotalPriceForOrders(0));
    }
}
