package com.company;

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
            int k=0;
            for (int i=0;i< Noten.length;i++)
            {
                int number=9;
                if (Noten[i]>38 && Noten[i]!=100)
                {
                    int multiple=40;//die erste Vielfache von 5 die grosser als 38 ist, ist 40
                    while (multiple<=Noten[i])//wir suchen den nachsten Vielfachen von 5
                    {
                        multiple=5*number;
                        number++;
                    }
                    if (multiple-Noten[i]<3){
                        result[k]=multiple;
                        k++;
                    }
                }

            }
            int j=0;
            int[] resultResize=new int[k];
            for (int el:result)
                if (el!=0){
                    resultResize[j]=el;
                    j++;
                }
            return resultResize;
        }
        // Die Methode gibt die maximale  abgerundete Note zurueck
        public int maxNote(int[] Noten)
        {
            int [] abgerundeteNoten=abgerundet(Noten);
            int maxEl=abgerundeteNoten[0];
            for (int i=0;i<abgerundeteNoten.length;i++)
            {
                if(abgerundeteNoten[i]>maxEl)
                {
                    maxEl=abgerundeteNoten[i];
                }
            }

            return maxEl;
        }


    }
