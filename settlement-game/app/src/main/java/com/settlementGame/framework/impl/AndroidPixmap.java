package com.settlementGame.framework.impl;

import android.graphics.Bitmap;

import com.settlementGame.framework.Graphics;
import com.settlementGame.framework.Pixmap;

public class AndroidPixmap implements Pixmap {

    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public Graphics.PixmapFormat getFormat() {
        return format;
    }

    public void dispose() {
        bitmap.recycle();
    }
}