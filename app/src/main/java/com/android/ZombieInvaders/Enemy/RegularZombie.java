package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.dashboard.DashboardImageView;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;

import com.android.ZombieInvaders.Bullet;
import com.android.ZombieInvaders.Soldier;

import java.util.ArrayList;

/**
 * Created by Mustafa Sabur and Okan Ok
 */
public class RegularZombie extends Zombie {

    //private int timeCounter;
    //private MoveableGameObject target;


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
        setSpeed(5);

        //if(getCollidingObject() == target.getCollidingObject()){        }

        timeCounter++;
        if(timeCounter % 4 == 0) {
            this.moveTowardsAPoint(target.getX(), target.getY());
        }

        if(getY() > target.getY() + 200){
            deleteThisGameObject();
            ZombieControler.nZombies--;
            System.out.println("RegularZombie deleted");
            ((Soldier)target).increaseScore(10);

        }

        ArrayList<GameObject> gotHit = getCollidedObjects();
        if (gotHit != null){
            for (GameObject g: gotHit){
                if (g instanceof Bullet){
                    deleteThisGameObject();
                    g.deleteThisGameObject();
                    ((Soldier)target).increaseScore(20);

                }
            }
        }
    }


}
