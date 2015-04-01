package com.android.ZombieInvaders;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.gameengine.icadroids.dashboard.DashboardImageView;
import android.gameengine.icadroids.dashboard.DashboardTextView;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.engine.Viewport;
import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.sound.MusicPlayer;
import android.view.View;

import com.android.ZombieInvaders.Enemy.ZombieControler;

import java.util.ArrayList;

/**
 * Main class of the game 'ZombieInvaders'.
 * @author Mustafa Sabur and Okan Ok
 */
public class ZombieInvaders extends GameEngine implements IAlarm{

	private Soldier soldier; // MoveableGmeObject Soldier, player in the game
	private View scoreDisplay, ammoDisplay, levelDisplay; //Dashboard for displaying the score and ammo.
    private ZombieControler zombieControler; //Controller how many Zombies are spawned en how fast.
    private ArrayList<DashboardImageView> hearts = new ArrayList<>(); //Holds a list of players hearts(life).
    private static int level = 2; //Game level.
    private int prevScore = 0;
    private Alarm alarm= new Alarm(7, 10, this);
    private boolean newMap = false;

    /**Initialize the game, create objects and level*/
	@Override
	protected void initialize() {

		// Set up control mechanisms to use
		TouchInput.use = false;
		MotionSensor.use = false;
		OnScreenButtons.use = true;

        //create land
        setTileMap(ZombieLand.createTileEnvironment(level));
        setBackground(ZombieLand.bgImage(level));

        //create player
		soldier = new Soldier(this);
        //add player to the game
		addGameObject(soldier, 850, getScreenHeight()*5-200);


        //add background music
        MusicPlayer.play("doomtheme", true);

        //create zombies
        zombieControler = new ZombieControler(this, 20, 3);


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

        //create score display
        scoreDisplay = new DashboardTextView(this);
        addToDashboard(scoreDisplay);

        ZombieLand.createDashboard(scoreDisplay, 192, 0, 96);
        //create ammo display
        ammoDisplay = new DashboardTextView(this);
        addToDashboard(ammoDisplay);
        ZombieLand.createDashboard(ammoDisplay, 16, 0, 96);
        //create hearts
        for (int i=0; i< 3; i++) {
            hearts.add(i, new DashboardImageView(this, "heart"));
            addToDashboard(hearts.get(i));
            ZombieLand.createDashboard(hearts.get(i), 40, 0, 96);
        }

        //create new dashboard
        addDashboard();

        //create level display
        levelDisplay = new DashboardTextView(this);
        addToDashboard(levelDisplay);
        ZombieLand.createDashboard(levelDisplay, 700, 500, 200);

        //create new dashboard
        addDashboard();
	}



    public Soldier getSoldier() {
        return soldier;
    }
    public ArrayList<DashboardImageView> getHearts() {
        return hearts;
    }


	/**Update the game.*/
	@Override
	public void update() {
		super.update();
        ((DashboardTextView)scoreDisplay).setTextString(" Score: " + String.valueOf(this.soldier.getScore()));
        ((DashboardTextView)ammoDisplay).setTextString(" Ammo: " + String.valueOf(this.soldier.getAmmo()));
        if (soldier.getScore() >= prevScore + 50) {
            prevScore = soldier.getScore();
            level++;
            if (level % 3 == 0) newMap = true;
            ((DashboardTextView)levelDisplay).setTextString(" Level: " + String.valueOf(level));

            soldier.setWalkingspeed(soldier.getWalkingspeed() - 5);
            alarm.startAlarm();
            printDebugInfo("hmm", "hmm1");
            pause();
            printDebugInfo("hmm", "hmm2");
            addAlarm(alarm);
            resume();
        }
        if (newMap){
            newMap = false;
            setTileMap(ZombieLand.createTileEnvironment(level));
            setBackground(ZombieLand.bgImage(level));
        }
        printDebugInfo("hmm", "hmm5");

    }

    @Override
    public void triggerAlarm(int alarmID) {
        //resume();
        //printDebugInfo("hmm", "hmm");
    }
}