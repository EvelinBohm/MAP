package com.company;

class Aufgabe2{

        //Die Methode findet die maximale Zahl im Array
        public int maxZahl(int [] ListNr)
        {
            int maxEl=ListNr[0];

            for (int i=0;i<ListNr.length;i++)
            {
                if(ListNr[i]>maxEl)
                {
                    maxEl=ListNr[i];
                }
            }
            return maxEl;
        }

        // Die Methode findet minimale Zahl im Aarray
        public int minZahl(int [] ListNr)
        {
            int minElem=ListNr[0];

            for (int i=0;i<ListNr.length;i++)
            {

                if(ListNr[i]<minElem)
                {
                    minElem=ListNr[i];
                }
            }
            return minElem;
        }

        // Die Methode bestimmt die maximale Summe von n-1 Zahlen
        public int maxSum(int[] ListNr)
        {
            int sum=0;
            int minElem=minZahl(ListNr);

            for (int i=0;i<ListNr.length;i++)
            {
                sum+=ListNr[i];

            }
            return sum-minElem;
        }

        // Die Methode bestimmt die minimale Summe von n-1 Zahlen
        public int minSum(int[] ListNr)
        {
            int sum=0;
            int maxElem=maxZahl(ListNr);

            for (int i:ListNr)
            {
                sum+=i;
            }
            return sum-maxElem;
        }

    }
