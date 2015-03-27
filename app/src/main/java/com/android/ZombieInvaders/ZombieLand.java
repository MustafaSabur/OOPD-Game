package com.android.ZombieInvaders;

import android.gameengine.icadroids.dashboard.DashboardImageView;
import android.gameengine.icadroids.dashboard.DashboardTextView;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

/**
 * Created by Sabur on 26/03/15.
 */
public class ZombieLand{

    public static GameTiles createTileEnvironment(int level) {
        String[] tileImagesNames = { "tree1", "tree2", "tree3", "empty", "empty", "empty", "empty", "bgtexture2"};

        Random r = new Random();

        int tilemap[][] = new int[56][19];

        for (int i = 0; i < tilemap.length; i++) {
            for (int j = 0; j < tilemap[i].length; j++){
                if(j > 1 && j < (tilemap[i].length - 2)){
                    tilemap[i][j]= r.nextInt(5) + 3;
                }
                else tilemap[i][j] = r.nextInt(3);
            }
        }
        for (int i = 44; i < tilemap.length; i++) {
            for (int j = 0; j < tilemap[i].length; j++){
                tilemap[i][j]= tilemap[i-44][j];

            }
        }

        GameTiles Tiles = new GameTiles(tileImagesNames, tilemap, 96);
        return Tiles;
    }

    public static void createDashboard(View d, int x, int y, int height){

        if(d instanceof DashboardTextView) {
            ((DashboardTextView)d).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
            ((DashboardTextView)d).setTextColor(Color.LTGRAY);
            ((DashboardTextView)d).setWidgetBackgroundColor(Color.argb(170, 100, 150, 100));
            ((DashboardTextView)d).setWidgetX(x);
            ((DashboardTextView)d).setWidgetY(y);
            ((DashboardTextView)d).setWidgetHeight(height);
            ((DashboardTextView)d).setWidgetWidth(450);
            //((DashboardTextView) d).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else if (d instanceof DashboardImageView) {
            ((DashboardImageView)d).setWidgetX(x);
            ((DashboardImageView)d).setWidgetY(y);
            ((DashboardImageView)d).setWidgetHeight(height);
            //((DashboardImageView)d).setWidgetWidth(400);

        }
    }

    public static String bgImage(int level){
        switch (level){
            case 1:
                return "backgroundgrass";

            default:
                return "backgroundgrass";

        }
    }
}
