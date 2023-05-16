package com.settlementGame.game.terrain;

import android.graphics.Color;
import android.graphics.Rect;

import com.settlementGame.R;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;
import com.settlementGame.game.SettlementGame;
import com.settlementGame.game.gameObject.Walkable;
import com.settlementGame.game.screen.Button;
import com.settlementGame.game.screen.GameScreen;

import java.util.ArrayList;

public class WorldInfo {

    private static Button ret = new Button("return", new Rect(50, 150, 50 + 200, 150 + 100), new Rect(0, 0, 0, 0));

    public static void drawMenu(Graphics g, float scale){

        // return button
        ret.draw(g,scale);

        g.drawRect(SettlementGame.WIDTH * 0.2f, SettlementGame.HEIGHT * 0.1f, SettlementGame.WIDTH * 0.7f, SettlementGame.HEIGHT * 0.8f, Color.rgb(24, 74, 255));
        g.drawText("WorldInfo",SettlementGame.WIDTH * 0.2f + 50, SettlementGame.HEIGHT * 0.1f + 200);

    }

    public static void updateMenu(Input.TouchEvent e){
        ret.update(e);
        // todo make scrollable
        if(ret.clicked(e)){
            GameScreen.showWorldMenu = false;
            GameScreen.showNoMenu = true;
        }
    }
}
