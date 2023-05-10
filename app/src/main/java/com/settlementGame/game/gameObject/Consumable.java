package com.settlementGame.game.gameObject;

import com.settlementGame.framework.Graphics;

public class Consumable extends GameObject{

    // todo all temp
    public static Consumable GrassSeed = new Consumable("Grass Seed", 0);

    public Consumable(String n, int i){
        super(n, 0,1, 1,i);
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        // todo
    }

    public static Consumable createConsumable(Consumable cons){
        return new Consumable(cons.name, cons.index);
    }

}
