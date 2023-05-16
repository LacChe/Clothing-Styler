package com.settlementGame.game.gameObject;

import android.graphics.Color;
import android.graphics.Rect;

import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;
import com.settlementGame.game.screen.Button;
import com.settlementGame.game.screen.GameScreen;

public class Blueprint extends GameObject{

    private static Button flooringButton = new Button("Flooring", new Rect(50, 150, 50 + 200, 150 + 100), new Rect(0, 0, 0, 0));
    private static Button blueprintButton = new Button("Blueprint", new Rect(50, 250, 50 + 200, 250 + 100), new Rect(0, 0, 0, 0));
    private static Button ret = new Button("return", new Rect(50, 350, 50 + 200, 350 + 100), new Rect(0, 0, 0, 0));

    private GameObject goal;

    // todo temp
    public Blueprint(String n, int x, int y, int i){
        super(n,0,1, 1, i);
    }

    public void draw(Graphics g, float worldOffSetX, float worldOffSetY, int i, int j, float scale){
        // todo
    }

    public static void drawMainMenu(Graphics g, float scale){

        // return button
        flooringButton.draw(g, scale);
        blueprintButton.draw(g, scale);
        ret.draw(g, scale);

        // g.drawRect(SettlementGame.WIDTH * 0.2f, SettlementGame.HEIGHT * 0.1f, SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.8f, Color.rgb(24, 74, 255));
        // g.drawText("Blueprint",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);

    }

    public static void updateMainMenu(Input.TouchEvent e){
        flooringButton.update(e);
        blueprintButton.update(e);
        ret.update(e);

        // todo make scrollable
        if(ret.clicked(e)){
            GameScreen.showBlueprintMenu = false;
            GameScreen.showNoMenu = true;
        }
    }

}
