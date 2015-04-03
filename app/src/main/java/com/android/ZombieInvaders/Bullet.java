package com.android.ZombieInvaders;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Bullet can be fired by the player in levelDisplayTimer straight line.
 * @author Mustafa Sabur and Okan Ok
 */
public class Bullet extends MoveableGameObject {

    private ZombieInvaders mygame; //reference to the game
    private MoveableGameObject shooter;

    public Bullet(ZombieInvaders mygame, int speed, MoveableGameObject shooter) {
        this.mygame = mygame;
        setSprite("icebullet");
        setSpeed(speed);
        setDirection(0);
        this.shooter = shooter;

    }

    public Bullet(ZombieInvaders mygame, String image, int speed, int direction, MoveableGameObject shooter) {
	    this.mygame = mygame;
	    setSprite(image);
        setSpeed(speed);
        setDirection(direction);
        this.shooter = shooter;
    }

    public MoveableGameObject getShooter() {
        return shooter;
    }

    /** Update Bullet every cycle of the game loop.*/
    @Override
    public void update() {
        super.update();
        if (!mygame.isInViewport(this) && this.getY() < mygame.getScreenHeight()*4){
            deleteThisGameObject();
            mygame.printDebugInfo("bullet", "deleted");
        }
    }
}
