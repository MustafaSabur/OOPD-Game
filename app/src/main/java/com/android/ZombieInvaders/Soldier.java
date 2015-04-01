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
 * when a zombie touches the soldier, he will lose one life.
 * @author Mustafa Sabur and Okan ok
 */
public class Soldier extends MoveableGameObject implements IAlarm {
	private ZombieInvaders mygame;
	private int score;
    private int ammo;
    private static boolean ableToFire, ableToDie = true;
    private Alarm fireRate, dyingRate;
    private int walkingspeed = -20;
    private int hp = 3;

	public Soldier(ZombieInvaders mygame)
	{
		this.mygame = mygame;
		setSprite("soldier", 8);
        setAnimationSpeed(3);
        setySpeed(walkingspeed);
        ammo = 100;
        score = 0;
        fireRate = new Alarm(5, 15, this);
        dyingRate = new Alarm(6, 15, this);
        dyingRate.startAlarm();
        fireRate.startAlarm();
	}

    public int getAmmo() {
        return ammo;
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

    public Bullet createBullet(){
        return new Bullet(mygame, 50 -(walkingspeed));
    }

    /** update 'Soldier': handle collisions and input from buttons / motion sensor */
	@Override
	public void update()
	{
		super.update();

        super.startAnimate();
        if(getY() <= 880){
            setY(getY()+(1080*4 + (walkingspeed)));
        }

        if(getX() < 150 || getX() > (mygame.getScreenWidth() - 280)){
            setySpeed(walkingspeed/2);
        }
        else setySpeed(walkingspeed);

		// collisions with objects
		ArrayList<GameObject> gotHit = getCollidedObjects();
		if (gotHit != null)
		{
			for (GameObject g : gotHit)
			{
//				if (g instanceof Bullet)
//				{
//					score = score + ((Bullet) g).getPoints();
//					Log.d("hapje!!!", "score is nu " + score);
//					mygame.deleteGameObject(g);
//				} else
                if (g instanceof Zombie){

                    if (g.getY() < getY() && g.getY() > getY()-100) {
                        if(!((Zombie)g).checkIfDead()) {
                            ((Zombie) g).setySpeed(walkingspeed / 2);
                            if (hp > 0 && ableToDie) {
                                ableToDie = false;
                                dyingRate.restartAlarm();
                                System.out.println("" + hp);
                                mygame.getHearts().get(hp - 1).setResourceName("empty");
                                hp--;
                            } else if (hp <= 0) {
                                mygame.pause();
                                mygame.addToDashboard(new DashboardImageView(mygame, "gameover"));
                                MusicPlayer.stop();
                            }
                        }
                    }
				}
			}
		}

		// Handle input.
		if (OnScreenButtons.dPadRight && getX() < mygame.getScreenWidth() - 100)
		{
            movePlayer(10, 0);
		}
		if (OnScreenButtons.dPadLeft && getX() > -20)
		{
            movePlayer(-10, 0);
		}
        if (OnScreenButtons.buttonA){
            if(ableToFire && ammo != 0){
                ableToFire = false;
                fireRate.restartAlarm();
                mygame.addGameObject(createBullet(), (int) getCenterX(), (int) getCenterY() - 100);
                mygame.vibrate(100);
                if(getY() <= mygame.getScreenHeight()*2 - 200){
                    mygame.addGameObject(createBullet(), (int) getCenterX(),(int) getCenterY()+(mygame.getScreenHeight()*4 - 100));
                }
                ammo--;
            }
        }
	}

	/**
	 * Get the score
	 * @return current value of score
	 */
	public int getScore()
	{
		return score;
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
