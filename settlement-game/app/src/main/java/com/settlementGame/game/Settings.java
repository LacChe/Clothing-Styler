package com.settlementGame.game;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;

import com.settlementGame.framework.FileIO;
import com.settlementGame.framework.impl.AndroidGame;
import com.settlementGame.framework.impl.Triplet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

// this class manages saving, loading etc
public class Settings {

    public static final boolean TRIAL_VERSION = false;

    public static int soundEnabled = 1;
    public static int coin = 0;
    public static int hasSavedGame = 0;

    public static void load(FileIO files) {
        /*
        try {
            Log.d("loading", "load");
            InputStream inputStream = AndroidGame.AGame.openFileInput("settlementGame.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader in = new BufferedReader(inputStreamReader);

                soundEnabled = Integer.parseInt(in.readLine());
                coin = Integer.parseInt(in.readLine());
                hasSavedGame = Integer.parseInt(in.readLine());

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
         */
    }

    public static void save(FileIO files) {
        /*
        try {
            Log.d("saving", "save");
            OutputStreamWriter out = new OutputStreamWriter(AndroidGame.AGame.openFileOutput("settlementGame.txt", Context.MODE_PRIVATE));
            // BufferedWriter out = new BufferedWriter(outputStreamWriter);

            out.write(soundEnabled + "\n");
            out.write(coin + "\n");
            out.write(hasSavedGame + "\n");

            out.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
         */
    }
}