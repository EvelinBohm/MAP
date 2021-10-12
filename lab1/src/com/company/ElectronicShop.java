package com.company;

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
