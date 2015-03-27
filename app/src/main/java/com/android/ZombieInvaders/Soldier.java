package com.android.ZombieInvaders;

import java.util.ArrayList;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.gameengine.icadroids.dashboard.DashboardImageView;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;

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
    private final int WALKINGSPEED = -5;
    private final int SLOW_WALKINGSPEED = -5;
    private int hp = 3;

	public Soldier(ZombieInvaders mygame)
	{
		this.mygame = mygame;
		setSprite("soldier", 8);
        setAnimationSpeed(3);
        setySpeed(WALKINGSPEED);
        ammo = 100;
        score = 0;
        fireRate = new Alarm(5, 15, this);
        dyingRate = new Alarm(6, 15, this);
        dyingRate.startAlarm();
        fireRate.startAlarm();
	}

//    public static boolean getIsAbleToFire() {
//        return ableToFire;
//    }
//
//    public static void setAbleToFire(boolean fire) {
//        ableToFire = fire;
//    }

    public int getAmmo() {
        return ammo;
    }

    public Bullet createBullet(){
        return new Bullet(mygame);
    }

    /** update 'Soldier': handle collisions and input from buttons / motion sensor */
	@Override
	public void update()
	{
		super.update();

        super.startAnimate();
        if(getY() <= 1000){
            setY(getY()+(1080*4-200));
        }

        if(getX() < 150 || getX() > (mygame.getScreenWidth() - 280)){
            setySpeed(SLOW_WALKINGSPEED);
        }
        else setySpeed(WALKINGSPEED);

		// collisions with objects
		ArrayList<GameObject> gebotst = getCollidedObjects();
		if (gebotst != null)
		{
			for (GameObject g : gebotst)
			{
//				if (g instanceof Bullet)
//				{
//					score = score + ((Bullet) g).getPoints();
//					Log.d("hapje!!!", "score is nu " + score);
//					mygame.deleteGameObject(g);
//				} else
                if (g instanceof Zombie)
				{
                    ((Zombie) g).setySpeed(WALKINGSPEED);
				    //System.out.println("Geraakt");

                    if(hp > 0 && ableToDie) {
                        ableToDie = false;
                        dyingRate.restartAlarm();
                        System.out.println(""+ hp);
                        mygame.getHearts().get(hp-1).setResourceName("empty");
                        hp--;
                    }
                    else if (hp <= 0){
                        mygame.pause();
                        mygame.addToDashboard(new DashboardImageView(mygame, "gameover"));
                    }
				}
//                else{
//                    ((Zombie) g).setySpeed(20);
//                }
			}
		}

		// Handle input.
		if (OnScreenButtons.dPadRight && getX() < mygame.getScreenWidth() - 100)
		{
            movePlayer(10, 0);
			//setX(getX() + 10);
		}
		if (OnScreenButtons.dPadLeft && getX() > -20)
		{
            movePlayer(-10, 0);
            //setX(getX() - 10);
		}
        if (OnScreenButtons.buttonA){
            if(ableToFire && ammo != 0){
                ableToFire = false;
                fireRate.restartAlarm();
                mygame.addGameObject(createBullet(), (int) getCenterX(), (int) getCenterY() - 100);
                mygame.vibrate(100);
                if(getY() <= mygame.getScreenHeight() +1000){
                    mygame.addGameObject(createBullet(), (int) getCenterX(), getY()+(mygame.getScreenHeight()*4));
                }
                ammo--;
            }

        }
		
		// Example of how to use the touch screen.
		// To use this, comment out the input from OnScreenButtons and MotionSensor 
		// and switch the use-settings in class ZombieInvaders
		/*
		 	// get readings from the TouchInput
		 	float targetX = TouchInput.xPos;
		 	float targetY = TouchInput.xPos;
		 	// When using the viewport, translate screen locations to game world 
		 	Point p = mygame.translateToGamePosition(targetX, targetY);
		 	// Move in the direction of the point that has been touched
			setSpeed(8);
		 	moveTowardsAPoint(p.x, p.y);
		*/ 
	}

	/**
	 * Get the score
	 * 
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
