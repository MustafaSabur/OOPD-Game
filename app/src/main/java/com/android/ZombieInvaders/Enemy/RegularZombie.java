package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

import com.android.ZombieInvaders.Soldier;

import java.util.List;

/**
 * Created by Mustafa Sabur and Okan Ok
 */
public class RegularZombie extends Zombie {

    private int timeCounter;
    private MoveableGameObject target;

    public RegularZombie(MoveableGameObject target) {
        super("regularzombie",8);
        this.target = target;
        setAnimationSpeed(8);
        this.timeCounter = 0;
        setSpeed(4);

    }
    @Override
    public void update() {
        super.update();
        super.startAnimate();
        //System.out.println(target.getCollidingObject());
        //System.out.println("zombie: " +getCollidingObject());
        if(getCollidingObject() == target.getCollidingObject()){
            //System.out.println("lol");
        }

        timeCounter++;
        if (timeCounter % 4 == 0) {
            this.moveTowardsAPoint(target.getCenterX(), target.getCenterY());
        }
    }


}
