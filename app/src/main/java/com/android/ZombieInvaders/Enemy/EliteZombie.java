package com.android.ZombieInvaders.Enemy;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import com.android.ZombieInvaders.Bullet;
import com.android.ZombieInvaders.Soldier;
import com.android.ZombieInvaders.ZombieInvaders;

import java.util.Random;

/**
 * A Controller that generates zombies and puts them into the game
 * randomly.
 * @author Mustafa Sabur and Okan Ok.
 */
public class EliteZombie extends Zombie implements IAlarm {
    private ZombieInvaders mygame;
    private Alarm fireRate;
    private boolean ableToFire;
    Random r = new Random();
    private int movement;
    private int bounceCount = 0;

    public EliteZombie(Soldier player,ZombieInvaders mygame){
        super(player,"szombie",8,100,30);
        setAnimationSpeed(3);
        startAnimate();
        this.mygame= mygame;
        fireRate = new Alarm(7, 40, this);
        movement = r.nextInt(5) + 2;
        ableToFire = false;
    }

    // sets up the bullet class
    public Bullet createBullet(){
        return new Bullet(mygame, "zbullet", 20, 180, this);
    }

    // shoots when the alarm is triggered
    private void shoot(){
        if(ableToFire && !isDead() ) {
            ableToFire = false;
            fireRate.restartAlarm();
            mygame.addGameObject(createBullet(), (int) getCenterX(), (int) getCenterY() + 100);
        }
    }

    @Override
    public void triggerAlarm(int alarmID) {
        switch (alarmID) {
            case 7:
                ableToFire = true;
                break;
        }
    }

    public void update(){
        super.update();
        doDeadAction("szombiedead",1,48);
        deleteIfOffScreen();
        eliteMovement();
        shoot();
        movePlayer(movement, 0);
    }

    private void eliteMovement(){

        if(getY() > target.getY() - 800 && !isDead()){
            fireRate.startAlarm();
            setY(target.getY() - 800);
        }

        if (isDead()){
            movement = 0;
        }


        if (bounceCount < 1){
            if (getX() < 0){
                movement = -(movement);
                movePlayer(1, 0);
                bounceCount++;
            }
            else if (getX() > 1750){
                movement = -(movement);
                movePlayer(-1, 0);
                bounceCount++;
            }
        }
    }
}
