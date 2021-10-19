package com.company;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private String firstname;
    private String lastname;
    private  List<Order>orders;

    public User(String firstname,String lastname)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.orders=new ArrayList<Order>();

    }
    public void addOrder( Order newOrder)
    {
        orders.add(newOrder);
    }
    public double calculateTotalPriceForOrders(int index)
    {
        return orders.get(index).calculateTotalPrice();
    }
    public double  calculateTotalPriceForAllOrders()
    {
        double sum=0;
        for (Order x:orders)
        {
            sum+=x.calculateTotalPrice();
        }
        return sum;
    }


}
