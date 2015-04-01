package com.android.ZombieInvaders;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Bullet can be fired by the player in a straight line.
 * @author Mustafa Sabur and Okan Ok
 */
public class Bullet extends MoveableGameObject {

    /**
     * reference to the game
     */
    private ZombieInvaders mygame;

    /**
     * Constructors of Bullet
     */
    public Bullet(ZombieInvaders mygame) {
        this.mygame = mygame;
        setSprite("icebullet");
        setSpeed(50);
        setDirection(0);

    }

    public Bullet(ZombieInvaders mygame, String image, int speed, int direction) {
	    this.mygame = mygame;
	    setSprite(image);
        setSpeed(speed);
        setDirection(direction);
    }


    /**
     * Update Bullet every cycle of the game loop.
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
