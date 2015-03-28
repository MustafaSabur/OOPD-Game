package com.android.ZombieInvaders;

import android.gameengine.icadroids.objects.*;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

import java.util.List;

/**
 * Bullet can be fired by the player in a straight line.
 */
public class Bullet extends MoveableGameObject implements ICollision {

    /**
     * reference to the game
     */
    private ZombieInvaders mygame;

    /**
     * Constructors of Bullet
     */
    public Bullet(ZombieInvaders mygame) {
        this.mygame = mygame;
        setSprite("raket");
        setSpeed(50);
        setDirection(0);

    }

    public Bullet(ZombieInvaders mygame, String image, int speed, int direction) {
	    this.mygame = mygame;
	    setSprite(image);
        setSpeed(speed);
        setDirection(direction);
    }

    @Override
    public void collisionOccurred(List<TileCollision> collidedTiles) {

    }

    /**
     * Update Bullet every cycle of the game loop.
     * @see android.gameengine.icadroids.objects.GameObject#update()
     */
    @Override
    public void update() {
        super.update();
        if (!mygame.isInViewport(this) && this.getY() < mygame.getScreenHeight()*4){
            deleteThisGameObject();
            mygame.printDebugInfo("bullet", "deleted");
        }


    }
}
