package com.android.ZombieInvaders;

import android.gameengine.icadroids.dashboard.DashboardImageView;
import android.gameengine.icadroids.dashboard.DashboardTextView;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import java.util.Random;
/**
 * Creates the background and dashboards for each level.
 * @author Mustafa Sabur and Okan Ok
 */
public class ZombieLand{

    public static GameTiles createTileEnvironment(int level) {
        String[] tileImagesNames;
        GameTiles Tiles;
        Random r = new Random();



        if (level >= 3){
            int tilemap[][] = new int[45][15];
            tileImagesNames = new String[]{"house1", "house2", "tree2","empty2"};


            for (int i = 0; i < tilemap.length; i++) {
                for (int j = 0; j < tilemap[i].length; j++) {
                    if (j > 1 && j < (tilemap[i].length - 2)) {
                        tilemap[i][j] = 3;
                    } else tilemap[i][j] = r.nextInt(4);
                }
            }
            for (int i = 36; i < tilemap.length; i++) {
                for (int j = 0; j < tilemap[i].length; j++) {
                    tilemap[i][j] = tilemap[i - 36][j];

                }
            }
            Tiles = new GameTiles(tileImagesNames, tilemap, 120);

        }else {
            int tilemap[][] = new int[45][15];
            tileImagesNames = new String[]{"tree1", "tree2", "tree3", "empty", "empty", "empty", "bgtexture1", "bgtexture2"};

            for (int i = 0; i < tilemap.length; i++) {
                for (int j = 0; j < tilemap[i].length; j++) {
                    if (j > 1 && j < (tilemap[i].length - 2)) {
                        tilemap[i][j] = r.nextInt(5) + 3;
                    } else tilemap[i][j] = r.nextInt(3);
                }
            }
            for (int i = 36; i < tilemap.length; i++) {
                for (int j = 0; j < tilemap[i].length; j++) {
                    tilemap[i][j] = tilemap[i - 36][j];

                }
            }
            Tiles = new GameTiles(tileImagesNames, tilemap, 120);
        }


        return Tiles;
    }

    public static void createDashboard(View d, int x, int y, int height){

        if(d instanceof DashboardTextView) {
            ((DashboardTextView)d).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
            ((DashboardTextView)d).setTextColor(Color.GREEN);
            ((DashboardTextView)d).setWidgetBackgroundColor(Color.argb(170, 150, 100, 100));
            ((DashboardTextView)d).setWidgetX(x);
            ((DashboardTextView)d).setWidgetY(y);
            ((DashboardTextView)d).setWidgetHeight(height);
            ((DashboardTextView)d).setWidgetWidth(450);
        }
        else if (d instanceof DashboardImageView) {
            ((DashboardImageView)d).setWidgetX(x);
            ((DashboardImageView)d).setWidgetY(y);
            ((DashboardImageView)d).setWidgetHeight(height);
        }
    }

    public static String bgImage(int level){
        if (level == 3){
            return "empty";
        }
        else return "backgroundgrass";
    }
}
