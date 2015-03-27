package com.android.ZombieInvaders;

import java.util.ArrayList;
import java.util.List;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButton;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

/**
 * Soldier is the main player object of the game. It moves forward through the game.
 * Avoids the zombie objects and tries to kill them with his weapon.
 * when a zombie touches the soldier, he will lose one life.
 * @author Mustafa Sabur and Okan ok
 */
public class Soldier extends MoveableGameObject implements ICollision, IAlarm {
	private ZombieInvaders mygame;
	private int score;
    private int ammo;
    private static boolean ableToFire = true;
    private Alarm fireRate;
    private static int walkingSpeed = -10;

	public Soldier(ZombieInvaders mygame)
	{
		this.mygame = mygame;
		setSprite("soldier", 8);
        setAnimationSpeed(3);
        setySpeed(walkingSpeed);
        ammo = 100;
        score = 0;
        fireRate = new Alarm(5, 15, this);
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
            setySpeed(-5);
        }
        else setySpeed(walkingSpeed);

		// collisions with objects
		ArrayList<GameObject> gebotst = getCollidedObjects();
//		if (gebotst != null)
//		{
//			for (GameObject g : gebotst)
//			{
//				if (g instanceof Bullet)
//				{
//					score = score + ((Bullet) g).getPoints();
//					// Log.d("hapje!!!", "score is nu " + score);
//					mygame.deleteGameObject(g);
//				} else if (g instanceof Zombie)
//				{
//					// Log.d("Gepakt", "Ai, wat nu...");
//				}
//			}
//		}
		// Handle input. Both on screen buttons and tilting are supported.
		// Buttons take precedence.
		boolean buttonPressed = false;

		if (OnScreenButtons.dPadRight
				|| (MotionSensor.tiltRight && !buttonPressed))
		{
            movePlayer(10, 0);
			//setX(getX() + 10);
		}
		if (OnScreenButtons.dPadLeft
				|| (MotionSensor.tiltLeft && !buttonPressed))
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
	 * Handle tile collisions.
	 * 
	 * @see android.gameengine.icadroids.objects.collisions.ICollision#collisionOccurred(java.util.List)
	 */
	@Override
	public void collisionOccurred(List<TileCollision> collidedTiles)
	{
//		for (TileCollision tc : collidedTiles)
//		{
//			if (tc.theTile.getTileType() < 3)
//			{
//                setySpeed(-5);
//                //moveUpToTileSide(tc);
//                //movePlayer(-200, 0);
//                //undoMove();
//				//
//				//break;
//			}
//            else setySpeed(-20);
//		}
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
        ableToFire = true;
    }
}
