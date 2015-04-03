package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;

import com.android.ZombieInvaders.Soldier;

import java.util.Random;


/**
 * Created by Mustafa Sabur and Okan Ok
 */
public class FoolishZombie extends Zombie {

    //private static int walkingSpeed;
    private Random r = new Random();


    public FoolishZombie(Soldier player) {
        super(player,"foolishzombie",27,100,10);
        startAnimate();
        setAnimationSpeed(2);
        setSpeed(3);
        setDirection(r.nextInt(180) + 90);
    }
    @Override
    public void update() {
        super.update();
        doDeadAction("foolishzombiedead",27,96 );
        deleteIfOffScreen();

    }

}



