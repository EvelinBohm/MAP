package com.company;

import com.sun.source.tree.Scope;

import java.io.NotActiveException;
import java.util.Arrays;

public class Main
{

    public static void main(String[] args) {
        Uni universitat = new Uni();
        int[] GradeList = {40, 32, 21, 100, 90, 84, 71, 88, 86};
        System.out.println("\nAufgabe1:");
        System.out.println("Notenliste:"+ Arrays.toString(GradeList));
        int[] result = universitat.nichtAusreichend(GradeList);
        System.out.println("Aufgabe1.1(nicht ausreichende Noten):"+Arrays.toString(result));
        System.out.println("Aufgabe1.2(Durchschnitt):"+universitat.avg(GradeList));
        int[] result2 = universitat.abgerundet(GradeList);
        System.out.println("Aufgabe1.3(Abgerundete Noten):"+Arrays.toString(result2));
        System.out.println("Aufgabe1.4(Maximale abgerundete Note):"+universitat.maxNote(GradeList));


        System.out.println("---------------------------------------------------\nAufgabe2:");
        Aufgabe2 aufgabe2 = new Aufgabe2();
        int[] ListEvenNr = new int[]{2, 4, 12,90,36,28};
        System.out.println("Zahlenliste:"+ Arrays.toString(ListEvenNr));
        System.out.println("Aufgabe2.1(Max Zahl):"+aufgabe2.maxZahl(ListEvenNr));
        System.out.println("Aufgabe2.2(Min Zahl):"+aufgabe2.minZahl(ListEvenNr));
        System.out.println("Aufgabe2.3(Maximale Summe von n-1 Zahlen):"+aufgabe2.maxSum(ListEvenNr));
        System.out.println("Aufgabe2.4(Minimale Summe von n-1 Zahlen):"+aufgabe2.minSum(ListEvenNr));


        System.out.println("---------------------------------------------------\nAufgabe3:");
        Aufgabe3 aufgabe3 = new Aufgabe3();
        int[] FirstArrayNr = new int[]{1,3,0,0,0,0,0,0,0};
        int[] SecondArrayNr = new int[]{8,7,0,0,0,0,0,0,0};
        int[] FirstArrayNrDif = new int[]{8,3,0,0,0,0,0,0,0};
        int[] SecondArrayNrDif = new int[]{5,4,0,0,0,0,0,0,0};
        int[] FirstArrayNrDif2 = new int[]{8,6,0,0,0,0,0,0,0};
        int[] SecondArrayNrDif2 = new int[]{9,4,0,0,0,0,0,0,0};
        int[] MultiplicationArray = new int[]{2,3,6,0,0,0,0,0,0};
        int[] DivisionArray = new int[]{2,3,6,0,0,0,0,0,0};
        System.out.println("Summe:"+ Arrays.toString(aufgabe3.SumArrays(FirstArrayNr,SecondArrayNr)));
        System.out.println("Dif:"+ Arrays.toString(aufgabe3.SubArray(FirstArrayNrDif,SecondArrayNrDif)));
        System.out.println("Dif:"+ Arrays.toString(aufgabe3.SubArray(FirstArrayNrDif2,SecondArrayNrDif2)));
        System.out.println("Mul:"+ Arrays.toString(aufgabe3.MultipicationArray(MultiplicationArray,2)));
        System.out.println("Div:"+ Arrays.toString(aufgabe3.DivArray(DivisionArray,3)));
        aufgabe3.DivArray(DivisionArray,0);


        System.out.println("---------------------------------------------------\nAufgabe4:");
        ElectronicsShop aufgabe4 = new ElectronicsShop();
        int[] Keyboard = new int[]{20,34,15,35};
        int[] USB=new int[]{20,15,40,15};
        int[] Price=new int[]{15,40,25,29,33,12,89,28};
        int[] Keyboard2 = new int[]{40,60,50};
        int[] Keyboardfail = new int[]{60};
        int[] USB2=new int[]{8,12};
        System.out.println("Tastaturliste:"+ Arrays.toString(Keyboard));
        System.out.println("USB Liste:"+ Arrays.toString(USB));
        System.out.println("Gunstigste Tastatur:"+ (aufgabe4.cheapestKeyboar(Keyboard)));
        System.out.println("Teuerstes Produkt:"+ (aufgabe4.MostExpensivProduct(Keyboard,USB)));
        System.out.println("Preis:"+ Arrays.toString(Price));
        System.out.println("Buget: 30 \nTeuerstes budget Produkt:"+(aufgabe4.BudgetProduct(Price,30)));
        System.out.println("Neue TastaturListe:"+ Arrays.toString(Keyboard2));
        System.out.println("Neue ListeUSB:"+ Arrays.toString(USB2));
        int buget=60;
        System.out.println((String.format("Max Geldbetrag fur USB+Tastatur mit %d buget:"+ (aufgabe4.BudgetForUSBAndKeyboard(Keyboard2,USB2 ,60)),buget)));
        System.out.println("Neue Tastaturliste:"+ Arrays.toString(Keyboardfail));
        System.out.println((String.format("Max Geldbetrag fur USB+Tastatur mit %d buget:"+ (aufgabe4.BudgetForUSBAndKeyboard(Keyboardfail,USB2 ,60)),buget)));


    }
}
class Uni
{
    // Die Methode gibt ein Array zurück, die die Noten enthält, die nicht ausreichend sind
    // nicht ausreichend<40
    public int[] nichtAusreichend(int[] Noten)
    {
        int[] array=new int[Noten.length];
        int j=0;
        for (int i=0;i< Noten.length;i++)
        {
            if (Noten[i]<40)
            {
                array[j]=Noten[i];
                j++;
            }
        }
        // erstellt ein Array deren grosse die Anzahl der nicht ausreichenden Noten ist
        int[] result=new int[j];
        for (int i=0;i< j;i++)
        {
          result[i]=array[i];
        }

        return result;
    }


    // Die Methode gibt den Durchschnitt der Noten zurueck
    public double avg(int[] Noten)
    {
        double sum=0;
        for (int i=0;i< Noten.length;i++)
        {
           sum+=Noten[i];
        }

        return sum/ Noten.length;
    }


    // Die Methode rundet alle Noten auf bei denen die Differenz zu dem naechsten Vielfachen von 5 kleiner als 3 ist, die Note wird dann auf dass Vielfache aufgrundet
    public int[] abgerundet(int[] Noten)
    {
        int[] result=new int[Noten.length];
        for (int i=0;i< Noten.length;i++)
        {
            int number=9;
           if (Noten[i]<38 || Noten[i]==100) //  falls die Note kleiner als 38 oder gleich 100 ist dann wird keine Rundung gemacht
            {
                result[i]=Noten[i];
            }

            if (Noten[i]>38 )
            {
                int multiple=40;//die erste Vielfache von 5 die grosser als 38 ist, ist 40
                while (multiple<Noten[i])//wir suchen den nachsten Vielfachen von 5
                {
                    multiple=5*number;
                    number++;
                }
                if (multiple-Noten[i]<3){
                   result[i]=multiple;}
                else
                    result[i]=Noten[i];
            }

        }

        return result;
    }
    // Die Methode gibt die maximale  abgerundete Note zurueck
    public int maxNote(int[] Noten)
    {
        int [] abgerundeteNoten=abgerundet(Noten);
        for (int i=0;i< abgerundeteNoten.length-1;i++)
        for(int j=0;j< abgerundeteNoten.length-1-i;j++)
        if(abgerundeteNoten[j]<abgerundeteNoten[j+1])
        {
            int aux=abgerundeteNoten[j];
            abgerundeteNoten[j]=abgerundeteNoten[j+1];
            abgerundeteNoten[j+1]=aux;

        }
        return abgerundeteNoten[0];
    }


}
  class Aufgabe2{

    //Die Methode findet die maximale Zahl im Array
    public int maxZahl(int [] ListNr)
    {
        for (int i=0;i< ListNr.length-1;i++)
            for(int j=0;j< ListNr.length-1-i;j++)
                if(ListNr[j]<ListNr[j+1])
                {
                    int aux=ListNr[j];
                    ListNr[j]=ListNr[j+1];
                    ListNr[j+1]=aux;

                }
        return ListNr[0];
    }

    // Die Methode findet minimale Zahl im Aarray
    public int minZahl(int [] ListNr)
    {
        for (int i=0;i< ListNr.length-1;i++)
            for(int j=0;j< ListNr.length-1-i;j++)
                if(ListNr[j]>ListNr[j+1])
                {
                    int aux=ListNr[j];
                    ListNr[j]=ListNr[j+1];
                    ListNr[j+1]=aux;

                }
        return ListNr[0];
    }

    // Die Methode bestimmt die maximale Summe von n-1 Zahlen
    public int maxSum(int[] ListNr)
    {
        int sum=0;
        int minElem=ListNr[0];

        for (int i=0;i<ListNr.length;i++)
        {
            sum+=ListNr[i];
            if(ListNr[i]<minElem)
            {
                minElem=ListNr[i];
            }
        }
        return sum-minElem;
    }

      // Die Methode bestimmt die minimale Summe von n-1 Zahlen
      public int minSum(int[] ListNr)
      {
          int sum=0;
          int maxElem=ListNr[0];

          for (int i=0;i<ListNr.length;i++)
          {
              sum+=ListNr[i];
              if(ListNr[i]>maxElem)
              {
                  maxElem=ListNr[i];
              }
          }
          return sum-maxElem;
      }

}
class Aufgabe3
{

    // Die Methode gibt ein Array zurueck, dass die Summe von zwei Arrays berechnet (die Arrays representieren Zahlen)
       int[] SumArrays(int[] FirstArrayofNr,int[] SecondArrayofNr)
       {
            int[] result=new int[FirstArrayofNr.length];
            int carry=0;
            int sum=0;
            int sizeResult=0;
            for (int i=FirstArrayofNr.length-1;i>=0;i--)
            {
               sum=FirstArrayofNr[i]+SecondArrayofNr[i]+carry;
               carry=sum/10;
               result[i]=sum%10;
            }
            // falls das Array zu klein ist,muss sie vergrossert werden
           if (carry==1)
           {
               sizeResult=FirstArrayofNr.length+1;
               int[] finalArray=new int[sizeResult];
               finalArray[0]=1;
               int k=1;
               for (int i=0;i<result.length;i++)
               {
                   finalArray[k]=result[i];
                   k++;
               }
               return finalArray;
           }

            return result;
       }

    // Die Methode gibt ein Array zurueck, dass die Differenz von zwei Arrays berechnet (die Arrays representieren Zahlen)
    public int[] SubArray(int[] FirstArray,int[] SecondArray)
    {
        int FirstNr=0,SecondNr=0,result=0;
        int[] resultarr=new int[FirstArray.length];
        // Array wird in Zahl umgewandelt
        for (int el:FirstArray)
            FirstNr=10*FirstNr+el;
        for (int el:SecondArray)
            SecondNr=10*SecondNr+el;
       //falls die zweite Zahl kleiner als die Erste ist
        if (SecondNr<FirstNr)
        {
            for (int i = FirstArray.length - 1; i >= 0; i--)
            {
                if (FirstArray[i] < SecondArray[i] && i != 0)
                {
                    int el = FirstArray[i] + 10;
                    resultarr[i] = el - SecondArray[i];
                    FirstArray[i - 1] = FirstArray[i - 1] - 1;
                } else
                 resultarr[i] = FirstArray[i] - SecondArray[i];
                if (i == 0 && FirstArray[0] - SecondArray[0] == 0)
                {
                    int k = 0;
                    int[] minimizedArray = new int[FirstArray.length - 1];
                    for (int j = 1; j < FirstArray.length - 1; j++)
                    {
                        minimizedArray[k] = resultarr[j];
                        k++;
                    }
                    return minimizedArray;
                }
            }
            return resultarr;
        }
        else
            //falls die Differenz negativ ist
            result=SecondNr-FirstNr;
        String temp=Integer.toString(result);
        String firstDigit="-"+temp.charAt(0);// die erste Zahl bekommt ein - Zeichen
        int firstNr=Integer.parseInt(firstDigit);
        int []resultArray=new int[temp.length()];
        resultArray[0]=firstNr; //die negative Zahl wird in das Array eingefugt
        for (int i=1;i<temp.length();i++)
        {
            resultArray[i]=temp.charAt(i)-'0';

        }
        return resultArray;

    }


    int[] MultipicationArray(int[] FirstArrayofNr,int number)
    {
        int[] result=new int[FirstArrayofNr.length];
        int carry=0;
        int res=0;
        int sizeResult=0;
        for (int i=FirstArrayofNr.length-1;i>=0;i--)
        {
            res=FirstArrayofNr[i]*number+carry;
            carry=res/10;
            result[i]=res%10;
        }
        // falls das Array vergrossert werden muss
        if (carry>0)
        {
            sizeResult=FirstArrayofNr.length+1;
            int[] finalArray=new int[sizeResult];
            finalArray[0]=carry;
            int k=0;
            for (int i=1;i<sizeResult-1;i++)
            {
                finalArray[i]=result[k];
                k++;
            }
            return finalArray;
        }

        return result;
    }

    // eine Nummer die als Array dargestellt ist, soll durch eine ganze Zahl geteilt werden
    int[] DivArray(int[] FirstArrayofNr,int number)
    {
        //teilen mit 0 nicht moeglich
        if (number==0)
        {
           try
           {
               int res=FirstArrayofNr[0]/number;

           }
           catch (ArithmeticException e)
           {
               System.out.println("Divison by zero not possible");

           }
           return new int[0];
        }

        int[] result=new int[FirstArrayofNr.length];
        int div=0, lengthArray=FirstArrayofNr.length;
        int i=0,k=0;
        //die Ziffern werden der Reihe nach durch die gegeben Zahl geteilt
        while (i<lengthArray)
        {
          while( div<number && i<lengthArray)
          { div=div*10+FirstArrayofNr[i++];
            result[k++]=div/number;
          }
          div=div%number;
        }
        // resize wird gemacht, wenn die Teilung mit Rest ist
        if (result[0]==0)
        {
        int newLen=result.length-1;
        int[] newresult=new int[newLen];
        int newIndex=0;
        for(int index=1;index<result.length;index++)
        {
            newresult[newIndex]=result[index];
            newIndex+=1;
        }
        return newresult;
        }
        return result;

    }



}
class ElectronicsShop
{
    //Die Methode bestimmt die guenstigete Tastatur
   public int cheapestKeyboar(int[] ListKeyboards)
   {
       // min Element wird gesucht
       int minKeyboard=ListKeyboards[0];
       for (int i=1;i<ListKeyboards.length;i++)
       {
           if(ListKeyboards[i]<minKeyboard)
           {
               minKeyboard=ListKeyboards[i];
           }
       }

       return minKeyboard;
   }

   // Das teuerste Produkt aus den beiden Listen wird gesucht
    public int MostExpensivProduct(int[] ListKeyboards,int []ListUSB)
    {

        int newSize=ListKeyboards.length+ListUSB.length;
        int[] ConcatArray=new int[newSize];
        int pos=0;
        int maxel=ListKeyboards[0];
        // die Listen werden vereint
        for (int elem:ListKeyboards)
        {
            ConcatArray[pos]=elem;
            pos++;
        }
        for (int elem:ListUSB)
        {
            ConcatArray[pos]=elem;
            pos++;
        }
        // das maximale Element wird gesucht
        for (int max:ConcatArray)
        {
            if(max>maxel)
            {
                maxel=max;
            }
        }
        return maxel;
    }

    // die Methode bestimmt anhand eines Budgets den besten Preis
    public int BudgetProduct(int [] PriceList,int budget)
    {
        int ProductPrice=0;

        for ( int Price:PriceList)
        {
            if (Price<=budget && ProductPrice<Price)//das Produkt fur das gegebene Bugdet wird gesucht
                ProductPrice=Price;
        }
        return ProductPrice;
    }

    // bestimmt anhand eines Budgets den maximalen Geldbetrag mit dem man sich eine Kombination aus Tastatur und USB kaufen aknn
    public int BudgetForUSBAndKeyboard(int[] ListUSB,int[] ListKeyboards, int budget)
    {
        int MaxMoneySum=0;
        int FoundSum=0;
        for (int i:ListKeyboards)
            for (int j:ListUSB)
            {
                if (i+j<budget)//es wird ein maximaler Betrag gesucht
                    FoundSum=i+j;
                if (FoundSum>MaxMoneySum)// es wird uberpruft ob kein anderer maximaler Betrag existiert
                    MaxMoneySum=FoundSum;

            }
        if(MaxMoneySum==0)
            return -1;
    return MaxMoneySum;
    }
}