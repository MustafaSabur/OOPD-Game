package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import com.android.ZombieInvaders.ZombieInvaders;

import java.util.Random;

/**
 * A Controller that generates zombies and puts them into the game.
 * @author Mustafa Sabur and Okan Ok.
 */
public class ZombieControler implements IAlarm {
    private ZombieInvaders mygame;
    private Alarm myAlarm;
    public static int nZombies;
    Random r;
    private final int maxNZombies;

    /**
     * Create a Controller and set the first Alarm,
     */
    public ZombieControler(ZombieInvaders mygame, int time, int maxNZombies) {
        r = new Random();
	    this.mygame = mygame;
	    myAlarm = new Alarm(2, time, this);
	    myAlarm.startAlarm();
        nZombies = 0;
        this.maxNZombies = maxNZombies;

    }

    /**
     * When Alarm rings, create a Zombie and add it.
     * Set Alarm for next Zombie.
     */
    public void triggerAlarm(int alarmID) {
        int x = r.nextInt(1920);
        int y = (mygame.getSoldier().getY() - 1500);

        if (nZombies < maxNZombies) {
            Zombie z = new RegularZombie(mygame.getSoldier());
            mygame.addGameObject(z, x, y);
            nZombies++;
            mygame.printDebugInfo("zombie", "created");
        }
	    myAlarm.restartAlarm();
    }
}
