package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.util.Log;

import com.android.ZombieInvaders.ZombieInvaders;

import java.util.Random;

/**
 * A Controller that generates zombies and puts them into the game
 * randomly.
 * @author Mustafa Sabur and Okan Ok.
 */
public class ZombieControler implements IAlarm {
    private ZombieInvaders mygame;
    private Alarm myAlarm;
    Random r;

    /**
     * Create a Controller and set the first Alarm,
     */
    public ZombieControler(ZombieInvaders mygame, int time) {
        r = new Random();
	    this.mygame = mygame;
	    myAlarm = new Alarm(2, time, this);
	    myAlarm.startAlarm();

    }

    /**
     * When Alarm rings, create a strawberry and add it. 
     * Set Alarm for next strawberry.
     * 
     * @see android.gameengine.icadroids.alarms.IAlarm#triggerAlarm(int)
     */
    public void triggerAlarm(int alarmID) {
    // zombie maken
	//Log.d("ZombieController", "Alarm af");
	//Bullet s = new Bullet(mygame);
	// world size has not been fixed, put it in a block of 600*400 pixels
	//int x = 10 + (int) (570 * Math.random());
	//int y = 10 + (int) (370 * Math.random());
	//mygame.addGameObject(s, x, y);


        mygame.printDebugInfo("Soldier", "Y: "+ mygame.getSoldier().getY());

        int x = r.nextInt(1920);
        int y = (mygame.getSoldier().getY() - 1500);
        Zombie z = new RegularZombie(mygame.getSoldier());
        mygame.addGameObject(z, x, y);

        mygame.printDebugInfo("zombie", "created");

	    myAlarm.restartAlarm();
    }

}
