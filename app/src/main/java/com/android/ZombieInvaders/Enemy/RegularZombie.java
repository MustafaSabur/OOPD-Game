package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Created by Mustafa Sabur and Okan Ok
 */
public class RegularZombie extends Zombie {

    private int timeCounter;
    private MoveableGameObject target;


    public RegularZombie(MoveableGameObject target) {
        super("regularzombie",8);
        this.target = target;
        setAnimationSpeed(3);
        startAnimate();
        this.timeCounter = 0;


    }
    @Override
    public void update() {
        super.update();
        setSpeed(50);
        //System.out.println(target.getCollidingObject());
        //System.out.println("zombie: " +getCollidingObject());
        if(getCollidingObject() == target.getCollidingObject()){
            //System.out.println("lol");
        }

        timeCounter++;
        if(timeCounter % 4 == 0) {
            this.moveTowardsAPoint(target.getX(), target.getY());
        }

        if(getY() > target.getY() + 200){
            //deleteThisGameObject();
            //System.out.println("RegularZombie deleted");

        }
    }


}
