package com.company;

class Aufgabe3
{

    // Die Methode gibt ein Array zurueck, dass die Summe von zwei Arrays berechnet (die Arrays representieren Zahlen)
    int[] sumArrays(int[] FirstArrayofNr,int[] SecondArrayofNr)
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
    public int[] subArray(int[] FirstArray,int[] SecondArray)
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


    int[] multipicationArray(int[] FirstArrayofNr,int number)
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
    int[] divArray(int[] FirstArrayofNr,int number)
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
