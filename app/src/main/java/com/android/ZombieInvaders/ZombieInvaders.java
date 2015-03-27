package com.android.ZombieInvaders;

import android.gameengine.icadroids.dashboard.DashboardTextView;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.engine.Viewport;
import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.sound.MusicPlayer;
import android.graphics.Color;
import android.util.TypedValue;

import com.android.ZombieInvaders.Enemy.RegularZombie;
import com.android.ZombieInvaders.Enemy.Zombie;

/**
 * Main class of the game 'ZombieInvaders'.
 * 
 * @author Mustafa Sabur and Okan Ok
 */
public class ZombieInvaders extends GameEngine {

    private ZombieLand zombieLand;
	private Soldier soldier; // MoveableGmeObject Soldier, player in the game
	private DashboardTextView scoreDisplay, ammoDisplay; //Dashboard for displaying the score and ammo
    private Zombie zombie1;
    private int level = 1;

	/**Initialize the game, create objects and level*/
	@Override
	protected void initialize() {

		// Set up control mechanisms to use
		TouchInput.use = false;
		MotionSensor.use = false;
		OnScreenButtons.use = true;

        //create land
        zombieLand = new ZombieLand(level);
        setTileMap(zombieLand.getMyTiles());
        setBackground("backgroundgrass");

        //create player
		soldier = new Soldier(this);
        //add player to the game
		addGameObject(soldier, 850, getScreenHeight()*5-200);

        //add background music
        MusicPlayer.play("doomtheme", true);

        //create zombies
        zombie1 = new RegularZombie(soldier);
        addGameObject(zombie1, 900, getScreenHeight()*5 -900);


        // Switch viewport on
        Viewport.useViewport = true;
        // Make viewport follow the Soldier
        setZoomFactor(0.985f);
        //setZoomFactor(0.15f);
        setPlayer(soldier);
        // Soldier will be bottom center of the screen
        setPlayerPositionOnScreen(Viewport.PLAYER_CENTER, Viewport.PLAYER_BOTTOM);
        // Determines how fast the viewport reacts on player movement
        setPlayerPositionTolerance(0, 0);

        scoreDisplay = new DashboardTextView(this);
        ammoDisplay = new DashboardTextView(this);
        createDashboard(scoreDisplay, 192, 0, 96);
        createDashboard(ammoDisplay, 16, 0, 96);
		// Example of how to add an image to the dashboard.
		//DashboardImageView imageDisplay = new DashboardImageView(this, "bg");
		//addToDashboard(imageDisplay);
	}

    //public int getSoldierScore(){
    //    return soldier.getScore();
    //}
	
	private void createDashboard(DashboardTextView display, int x, int y, int height){

        display.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
        display.setTextColor(Color.LTGRAY);
        //display.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        addToDashboard(display);
		display.setWidgetX(x);
		display.setWidgetY(y);
        display.setWidgetHeight(height);
        display.setWidgetWidth(700);
        display.setWidgetBackgroundColor(Color.argb(170, 100, 150, 100));
	}

	/**Update the game.*/
	@Override
	public void update() {
		super.update();
		this.scoreDisplay.setTextString(" Score: " + String.valueOf(this.soldier.getScore()));
        this.ammoDisplay.setTextString(" Ammo: " + soldier.getAmmo());
        //Log.d("soldier", "Ypositie: "+soldier.getY());
//        if(soldier.getY()<3000){
//            soldier.setX(1080*5-100);
//            setPlayer(soldier);
//            setPlayerPositionOnScreen(Viewport.PLAYER_CENTER, Viewport.PLAYER_BOTTOM);
//        }
        if(soldier.getAmmo() < 70){
            //pause();
        }
    }
}