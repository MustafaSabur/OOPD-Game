package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.util.Log;
import com.android.ZombieInvaders.ZombieInvaders;
import java.util.Random;

/**
 * A Controller that generates zombies and puts them into the game.
 * @author Mustafa Sabur and Okan Ok.
 */
public class ZombieControler implements IAlarm {
    private ZombieInvaders mygame; //instance of the game
    private Alarm myAlarm; //trigger for creating a new Zombie
    public static int nZombies; //number of active instance of Zombie
    public static int maxNZombies; //max number of Zombies that can spawn
    private Random r; //need this to make the spawn positions and the kind of zombie random

    /**
     * Create a Controller and set the first Alarm,
     */
    public ZombieControler(ZombieInvaders mygame, int maxNZombies) {
        r = new Random();
	    this.mygame = mygame;
	    myAlarm = new Alarm(2, 1, this);
	    myAlarm.startAlarm();
        nZombies = 0;
        this.maxNZombies = maxNZombies;

    }

    /**
     * When Alarm rings, create a Zombie and add it.
     * Set Alarm for next Zombie.
     */
    public void triggerAlarm(int alarmID) {
        int x = r.nextInt(1800);
        int y = (mygame.getSoldier().getY() - 1500);

        if (nZombies < maxNZombies) {
            int zombieKind = r.nextInt(4);
            Zombie z;
            //zombieKind = 1;
            switch (zombieKind){
                case 0:
                    z = new CrawlerZombie(mygame.getSoldier());
                    break;
                case 1:
                    z = new EliteZombie(mygame.getSoldier(), mygame);
                    break;
                case 2:
                    z = new FoolishZombie(mygame.getSoldier());
                    break;
                default:
                    z = new RegularZombie(mygame.getSoldier());
                    break;
            }
            mygame.addGameObject(z, x, y);
            nZombies++;
            mygame.printDebugInfo("zombie", "created");
        }
        Log.d("Zombie", "max aantal: "+ maxNZombies);
	    myAlarm.restartAlarm();
    }
}
