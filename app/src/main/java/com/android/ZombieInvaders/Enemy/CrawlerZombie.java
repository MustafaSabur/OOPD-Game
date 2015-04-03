package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;

import com.android.ZombieInvaders.Soldier;

/**
 * A Controller that generates zombies and puts them into the game
 * randomly.
 * @author Mustafa Sabur and Okan Ok.
 */
public class CrawlerZombie extends Zombie {

    public CrawlerZombie(Soldier player){
        super(player,"czombie",18,200,25);
        setAnimationSpeed(3);
        startAnimate();
        setSpeed(2);
    }

    @Override
    public void update() {
        super.update();

        doDeadAction("czombiedead",11,48);
        moveToTarget(1);
        deleteIfOffScreen();
    }
}
