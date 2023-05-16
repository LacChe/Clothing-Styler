package com.settlementGame.game.screen;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import androidx.core.view.MotionEventCompat;

import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;

import java.util.List;

public class Button {

    private boolean fingerDown = false;

    private Rect rect, srcRect;
    private boolean checked = false;
    private String name;

    public Button(String name, Rect rect, Rect srcRect){
        this.name = name;
        this.rect = rect;
        this.srcRect = srcRect;
    }

    public void draw(Graphics g, float scale){
        // todo if selected change draw size
        // todo draw sprite
        g.drawRect(rect.left, rect.top, rect.width(), rect.height(), Color.rgb(255, 74, 24));
        g.drawText(name, rect.left, rect.bottom);
    }

    public void update(Input.TouchEvent e){

    }

    public boolean clicked(Input.TouchEvent e){
        return (e.type == Input.TouchEvent.TOUCH_DOWN && rect.contains(e.x, e.y));
    }

    public void check(){
        checked = !checked;
    }

    public boolean checked(){
        return checked;
    }

}