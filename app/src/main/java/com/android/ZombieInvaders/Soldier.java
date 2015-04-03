 package com.android.ZombieInvaders;

import java.util.ArrayList;
import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.gameengine.icadroids.dashboard.DashboardImageView;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.sound.MusicPlayer;
import com.android.ZombieInvaders.Enemy.Zombie;

/**
 * Soldier is the main player object of the game. It moves forward through the game.
 * Avoids the zombie objects and tries to kill them with his weapon.
 * when levelDisplayTimer zombie touches the soldier, he will lose one life.
 * @author Mustafa Sabur and Okan ok
 */
public class Soldier extends MoveableGameObject implements IAlarm {
	private ZombieInvaders mygame; // reference to the game
	private int score; //score for instance of soldier
    private int ammo; //amount of ammo left
    private static boolean ableToFire, ableToDie = true; //whether Soldier can fire or not
    private Alarm fireRate, dyingRate; //alarm triggers
    private int walkingspeed = -5; //speed of Soldier
    private int hp = 3; //hp of Soldier

	public Soldier(ZombieInvaders mygame)
	{
		this.mygame = mygame;
		setSprite("soldier", 8);
        setAnimationSpeed(3);
        startAnimate();
        setySpeed(walkingspeed);
        ammo = 10;
        score = 0;
        fireRate = new Alarm(5, 15, this);
        dyingRate = new Alarm(6, 15, this);
        dyingRate.startAlarm();
        fireRate.startAlarm();
	}

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getScore(){
        return score;
    }

    public void increaseScore(int scoreIncease) {
        score = score + scoreIncease;
    }

    public int getWalkingspeed() {
        return walkingspeed;
    }

    public void setWalkingspeed(int walkingspeed) {
        this.walkingspeed = walkingspeed;
    }

    private Bullet createBullet(){
        return new Bullet(mygame, 50 -(walkingspeed), this);
    }

    private void collision(){
        // collisions with objects
        ArrayList<GameObject> gotHit = getCollidedObjects();
        if (gotHit != null){
            for (GameObject g : gotHit){
                if (g instanceof Zombie){
                    if (g.getY() < getY() && g.getY() > getY()-100) {
                        if(!((Zombie)g).isDead()) {
                            ((Zombie) g).setySpeed(walkingspeed / 2);
                            if (hp > 0 && ableToDie) {
                                ableToDie = false;
                                dyingRate.restartAlarm();
                                System.out.println("" + hp);
                                mygame.getHearts().get(hp - 1).setResourceName("empty");
                                hp--;
                            }
                        }
                    }
                }
                else if(g instanceof Bullet){
                    if (hp > 0 && ableToDie && ((Bullet) g).getShooter() != this) {
                        ableToDie = false;
                        g.deleteThisGameObject();
                        dyingRate.restartAlarm();
                        System.out.println("" + hp);
                        mygame.getHearts().get(hp - 1).setResourceName("empty");
                        hp--;
                    }
                }                
            }
        }
    }

    private void handleInput() {
        // Handle input.
        if (OnScreenButtons.dPadRight && getX() < mygame.getScreenWidth() - 100)
        {
            movePlayer(15, 0);
        }
        if (OnScreenButtons.dPadLeft && getX() > -20)
        {
            movePlayer(-15, 0);
        }
        if (OnScreenButtons.buttonA){
            if(ableToFire && ammo != 0){
                ableToFire = false;
                fireRate.restartAlarm();
                mygame.addGameObject(createBullet(), (int) getCenterX(), (int) getCenterY() - 100);
                mygame.vibrate(100);
                if(getY() <= mygame.getScreenHeight()*2 - 200){
                    mygame.addGameObject(createBullet(), (int) getCenterX(), (int) getCenterY()+(mygame.getScreenHeight()*4 - 100));
                }
                ammo--;
            }
        }
    }

    /** update 'Soldier': handle collisions and input from buttons / motion sensor */
	@Override
	public void update(){
		super.update();

        //super.startAnimate();

        //soldier position and speed
        if(getY() <= 880){
            setY(getY()+(1080*4 + (walkingspeed)));
        }

        if(getX() < 150 || getX() > (mygame.getScreenWidth() - 280)){
            setySpeed(walkingspeed/2);
        }
        else setySpeed(walkingspeed);

        if (hp <= 0) {
            mygame.pause();
            mygame.addToDashboard(new DashboardImageView(mygame, "gameover"));
            MusicPlayer.stop();
        }

        handleInput();
        collision();
	}

    @Override
    public void triggerAlarm(int alarmID) {
        switch (alarmID){
            case 5:
                ableToFire = true;
                break;
            case 6:
                ableToDie = true;
                break;
        }
    }
}
