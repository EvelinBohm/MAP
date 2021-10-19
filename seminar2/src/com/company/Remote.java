package com.company;

public class Remote {
    private TV tv;
    private static int nrinstance=0;
    private static Remote instance;
    private Remote( TV tv)
    {
        this.tv=tv;
    }
   public static  Remote getInstance(TV tv)
   {
       if(nrinstance==0){
           instance= new Remote(tv);
           nrinstance++;
       }
       return instance;
   }
    public String channel_up(){
        return this.tv.channel_up();
    }
    public String channel_down(){
       return this.tv.channel_down();
    }
}
