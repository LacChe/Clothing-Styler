package com.settlementGame.game.gameObject;

public class Materials {

    private GameObject[] mats;
    private int[] amount;

    public Materials(GameObject[] mats, int[] amount){
        this.mats = mats;
        this.amount = amount;
    }

    public Materials(){
        this.mats = new GameObject[0];
        this.amount = new int[0];
    }

    public GameObject[] getMats(){
        return mats;
    }

    public int[] getAmounts(){
        return amount;
    }

    public static Materials createMaterials(Materials src){

        GameObject[] newMats = new GameObject[src.mats.length];
        int[] newAmount = new int[src.amount.length];

        for(int i = 0; i < newMats.length; i++){
            newMats[i] = src.mats[i];
            newAmount[i] = src.amount[i];
        }

        Materials newMaterials = new Materials(newMats, newAmount);
        return newMaterials;
    }

    public int getTotalAmount(){
        int amt = 0;
        for (int m = 0; m < mats.length; m++) {
            amt += amount[m];
        }
        return amt;
    }


}