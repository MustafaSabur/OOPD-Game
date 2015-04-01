package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * @author Mustafa Sabur and Okan Ok
 */
public class RegularZombie extends Zombie {

    public RegularZombie(MoveableGameObject target) {
        super(target, "rzombie",8, 100, 30);
        this.target = target;
        setAnimationSpeed(3);
        startAnimate();
        //this.timeCounter = 0;
        setySpeed(5);


    }
    @Override
    public void update() {
        super.update();
        //setSpeed(5);

        //if(getCollidingObject() == target.getCollidingObject()){        }

//        timeCounter++;
//        if(timeCounter % 4 == 0) {
//            this.moveTowardsAPoint(target.getX(), target.getY());
//        }
//
//        if(getY() > target.getY() + 200){
//            deleteThisGameObject();
//            ZombieControler.nZombies--;
//            System.out.println("RegularZombie deleted");
//            ((Soldier)target).increaseScore(10);
//
//        }
        doDeadAction("rzombiedead",7,48);
        moveToTarget();
        deleteIfOffScreen();



    }


}
