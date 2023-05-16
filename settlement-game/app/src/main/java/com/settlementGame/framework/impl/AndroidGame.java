package com.settlementGame.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.settlementGame.R;
import com.settlementGame.framework.Audio;
import com.settlementGame.framework.FileIO;
import com.settlementGame.framework.Game;
import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Input;
import com.settlementGame.framework.Screen;
import com.settlementGame.game.SettlementGame;

public abstract class AndroidGame extends Activity implements Game {

    public static MotionEvent EVENT;

    public static int WIDTH = -1, HEIGHT = -1;

    public Context mContext;
    //public static Resources R;

    public static AndroidGame AGame;

    public AndroidFastRenderView renderView;
    public Graphics graphics;
    public Audio audio;
    public Input input;
    public FileIO fileIO;
    public Screen screen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        /*
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        */

        boolean isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = !isLandscape ? Resources.getSystem().getDisplayMetrics().heightPixels : Resources.getSystem().getDisplayMetrics().widthPixels;
        int frameBufferHeight = !isLandscape ? Resources.getSystem().getDisplayMetrics().widthPixels : Resources.getSystem().getDisplayMetrics().heightPixels;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();
        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getStartScreen();
        setContentView(renderView);
        AGame = this;

        if((WIDTH == -1 || HEIGHT == -1) && false){
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point p = new Point(0, 0);
            display.getRealSize(p);
            WIDTH = p.x;
            HEIGHT = p.y;
        } else {
            WIDTH = graphics.getWidth();
            HEIGHT = graphics.getHeight();
        }
    }

    // todo nav bar doesnt auto hide
    @Override
    protected void onResume() {
        super.onResume();
        /*
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        */

    }

    public Input getInput() {
        return input;
    }

    public FileIO getFileIO() {
        return fileIO;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    public Screen getCurrentScreen() {
        return screen;
    }

}