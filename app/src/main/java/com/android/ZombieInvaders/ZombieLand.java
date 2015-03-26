package com.android.ZombieInvaders;

import android.gameengine.icadroids.tiles.GameTiles;
import java.util.Random;

/**
 * Created by Sabur on 26/03/15.
 */
public class ZombieLand{

    private GameTiles myTiles;

    public ZombieLand(int level){
        myTiles = createTileEnvironment(level);

    }

    public GameTiles getMyTiles() {
        return myTiles;
    }

    public GameTiles createTileEnvironment(int level) {
        String[] tileImagesNames = { "tree1", "tree2", "tree3", "bgtexture", "bgtexture", "bgtexture", "bgtexture1", "bgtexture2"};

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
        //setTileMap(myTiles);
        //Log.d("ZombieInvaders", "GameTiles created");
    }

}
