package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main
{

    public static void main(String[] args) {
        MatheAufgabe obj = new MatheAufgabe();
        System.out.println(obj.add(1, 2));
        System.out.println(obj.sub(2, 3));
        System.out.println(obj.perfekt(6));
        System.out.println(obj.perfekt(7));
        System.out.println(obj.mul(1, 3));
        System.out.println(obj.mul(4, 3));

        Wetterstation obj2 = new Wetterstation(new int[]{12, 14, 9, 12, 15, 16, 15, 15, 11, 8, 13, 13, 15, 12});
        System.out.println(obj2.avg());
        obj2.diff();
        System.out.print("Tage:");
        for (int i=0;i<2;i++)
        {
            System.out.print(obj2.diff()[i]+";");
        }
        System.out.println();
        obj2.display();
        //Fibo obj3 = new Fibo();
        //System.out.println(ob3.che;
    }
}
class MatheAufgabe{

    public double add(double x,double y){
        return x+y;
    }
    public double sub(double x,double y){
        return x-y;
    }

    public boolean perfekt(double x) {
        int sum = 0;
        for (int i = 1; i <= x / 2; i++){
            if (x % i == 0)

                sum += i;

    }
        return sum==x;
    }

    public double mul(double x,double y)
    {
        double nul=0;
        for (int i=1;i<=y;i++){
            nul=add(nul,x);
        }
        return nul;
    }
}
//2 Teil
class Wetterstation{


    private int temp[];
    public Wetterstation(int t[])
    {
        temp=t;
    };
    public double avg()
    {
        double sum=0;
        for(int i=0;i<14;i++)
        {
           sum=sum+temp[i];
        }
        return sum/14;
    }

    public double[] MaxMin()
    {
        int min=temp[0],max=temp[0];
        for(int i=1;i<14;i++)
        {
           if(temp[i]<min)
               min=temp[i];
            if(temp[i]>max)
                max=temp[i];
        }
        return new double[] {min,max};
    }
    public double[] diff()//int temp[])
    {

        int max_diff = 0, day1 = 1, day2=2;
        for (int i = 0; i < 13; i++) {
            int diff = Math.abs(temp[i] - temp[i + 1]);
            if (diff > max_diff) {
                max_diff = diff;
                day1 = temp[i];
                day2 = temp[i + 1];
            }

        }
     return new double[] {day1,day2};

    }
    public void display(){//int temp[]){
        System.out.print("Tag: ");
        for (int i=1;i<15;i++)
        {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("Temperatur: ");
        for (int i=0;i<14;i++)
        {
            System.out.print(temp[i]);
            System.out.print(" ");
        }
    }}
class Fibo
    {

        public int fibonacci(int n)
        {
            int nr1=0;
            int nr2=1;
            int sum=0;
            for (int i=3;i<=n;i++)
            {
               sum=sum+ nr1+nr2;
               nr1=nr2;
               nr2=sum;
            }
            return sum;
        }

        public boolean CheckIfNrAppearsInFibo (int x)
        {
            int nr1=0;
            int nr2=1;
            int sum=0;
            while (sum<x)
            {
                sum=sum+nr1+nr2;
                if (sum==x){
                    return true;
                }
                nr1=nr2;
                nr2=sum;
            }
            return false;

        }
        public List<Integer>  showFibo(int n)
        {
            int nr1=0;
            int nr2=1;
            int sum=0;
            List result=new ArrayList<Integer>();
            result.add(0);
            result.add(1);

            for (int i=3;i<=n;i++)
            {
                sum=nr1+nr2;
                result.add(sum);
                nr1=nr2;
                nr2=sum;
            }
            return result;
    }
}