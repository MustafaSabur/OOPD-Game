package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;
import android.util.Log;

import com.android.ZombieInvaders.Bullet;
import com.android.ZombieInvaders.Soldier;

import java.util.ArrayList;

/**
 * Zombie is a abstract MoveableGameObject class.
 * Extensions of Zombie can chase any other MoveableGameObject
 * In 'ZombieInvaders', it will chase 'Soldier'.
 * @author Mustafa Sabur and Okan Ok
 */
public abstract class Zombie extends MoveableGameObject {
    
    /**
     * counts time (that is calls on update()). Using the counter, we can create
     * behaviour at certain updates only, instead of always.
     */
    private int moveCounter;
    protected int timeCounter;

    private int hp;
    private boolean isDiying;
    private int killExp;
    
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
    public Zombie(MoveableGameObject target,String sprite, int nFrames, int hp, int killExp) {
        setSprite(sprite, nFrames);
        this.timeCounter = 0;
        this.target = target;
        this.hp = hp;
        this.killExp = killExp;
    }

    public boolean checkIfDead(){
        if (hp <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * update: change direction to target every 4th step only.
     * 
     * @see android.gameengine.icadroids.objects.MoveableGameObject#update()
     */
    @Override
    public void update() {
        super.update();

        if(target.getY() > getY() + 2500){
            setY(getY() + (1080*4));
        }


        ArrayList<GameObject> gotHit = getCollidedObjects();
        if (gotHit != null &&isDiying == false){
            for (GameObject g: gotHit){
                if (g instanceof Bullet){
                    hp -= 100;
                    g.deleteThisGameObject();
                    isDiying = true;
                    if(hp > 0){
                        isDiying = false;
                    }

                }
            }
        }
    }
    public void deleteIfOffScreen(){
        if(getY() > target.getY() + 200){
            deleteThisGameObject();
            Log.d("Zombie", "deleted");
            ZombieControler.nZombies--;
            if(checkIfDead()){
                ((Soldier)target).increaseScore(killExp);
            }
            else {
                ((Soldier)target).increaseScore(10);
            }
        }
    }

    public void moveToTarget(){
        moveCounter++;
        if(moveCounter % 4 == 0 && !checkIfDead()) {
            this.moveTowardsAPoint(target.getX(), target.getY());
        }
    }

    public void doDeadAction(String spriteName, int nFrames, int deleteTimer){
        if(checkIfDead()){
            timeCounter ++;
            if(timeCounter  == 2) {
                super.setSprite(spriteName, nFrames);
                super.setFrameNumber(0);
                super.setSpeed(0);
            }
            if(super.getCurrentFrame() > nFrames -2){
                stopAnimate();
            }
            if(timeCounter > deleteTimer){
                ((Soldier) target).increaseScore(killExp);
                deleteThisGameObject();
                ZombieControler.nZombies--;
                Log.d("Zombie", "deleted");
            }
        }
    }

}
