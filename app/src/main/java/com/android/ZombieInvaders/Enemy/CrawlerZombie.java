package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * A Controller that generates zombies and puts them into the game
 * randomly.
 * @author Mustafa Sabur and Okan Ok.
 */
public class CrawlerZombie extends Zombie {


    public CrawlerZombie(MoveableGameObject player){
        super(player,"regularzombie",8,200,40);
        setAnimationSpeed(3);
        startAnimate();
        setSpeed(2);
    }

    @Override
    public void update() {
        super.update();

        doDeadAction("regularzombiedead",7,48);
        moveToTarget();
        deleteIfOffScreen();
    }
}
