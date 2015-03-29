package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Zombie is a abstract MoveableGameObject class.
 * Extensions of Zombie can chase any other MoveableGameObject
 * In 'ZombieInvaders', it will chase 'Soldier'.
 * @Mustafa Sabur and Okan Ok
 */
public abstract class Zombie extends MoveableGameObject {
    
    /**
     * counts time (that is calls on update()). Using the counter, we can create
     * behaviour at certain updates only, instead of always.
     */
    protected int timeCounter;
    
    /**
     * The MoveableGameObject to be chased
     */
    protected MoveableGameObject target;

    /**
     * Create a Zombie
     * 
     * @param target 
     * 		the MoveableGameObject to be chased
     */
    public Zombie(MoveableGameObject target,String sprite, int nFrames) {
        this.target = target;
        setSprite(sprite, nFrames);
        this.timeCounter = 0;
    }
    
    public Zombie(String sprite, int nFrames) {
        setSprite(sprite, nFrames);
        this.timeCounter = 0;
    }

    /**
     * update: change direction to target every 4th step only.
     * 
     * @see android.gameengine.icadroids.objects.MoveableGameObject#update()
     */
    @Override
    public void update() {
    	super.update();
    }

    /**
     * Tile collision: monster bounces off all tiles, so use first collision
     * 
     * @see android.gameengine.icadroids.objects.collisions.ICollision#collisionOccurred(java.util.List)
     */
    

}
