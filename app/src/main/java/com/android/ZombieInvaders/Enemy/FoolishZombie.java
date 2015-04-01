package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;


/**
 * Created by Mustafa Sabur and Okan Ok
 */
public class FoolishZombie extends Zombie {



    private static int walkingSpeed;


    public FoolishZombie(MoveableGameObject player) {
        super(player,"foolishzombie",27,100,20);
        startAnimate();
        setAnimationSpeed(2);

        setSpeed(-3);

    }
    @Override
    public void update() {
        super.update();
        doDeadAction("foolishzombiedead",27,96 );
        deleteIfOffScreen();
    }

}



