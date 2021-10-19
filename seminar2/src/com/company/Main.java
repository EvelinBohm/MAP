package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SpielKarte s1=new SpielKarte(1,"kreuz");
            SpielKarte s2= new SpielKarte(2,"Herz");
        SpielKarte s3=new SpielKarte(2,"kreuz");
        //, new SpielKarte(3,"Pik")};
        SpielKarte[] sliste=new  SpielKarte[]{s1,s2,s3};
        Deck d= new Deck(sliste);
        for (SpielKarte x:d )
        {
            System.out.println(x);
        }
        TV tv=new TV(new String[] {"1","2","3" });
        System.out.println(tv.channel_up());
        System.out.println(tv.channel_down());

        //Remote remote=Remote.getInstance(tv);
        //Remote remote1=Remote.getInstance(tv);
        Remote remote1=tv.createRemote();
        Remote remote=tv.createRemote();

        System.out.println(remote.channel_up());
        System.out.println(remote1.channel_down());


    }




}
