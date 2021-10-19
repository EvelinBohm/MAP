package com.company;

public class TV {
   private String [] channels;
   private int index;
   public  TV(String[] channels)
   {
       this.channels=channels;
   }


    public String channel_up()
    {    this.index++;
        if (index<channels.length)
            return  this.channels[index];
        return this.channels[this.channels.length-1];
    }
    public String channel_down()
    { this.index--;
        if (index>=0)
            //return  this.channels[index];
            return this.channels[index];
        else
            return this.channels[0];
    }

    public Remote createRemote()
    {
       return Remote.getInstance(this);
    }

}
