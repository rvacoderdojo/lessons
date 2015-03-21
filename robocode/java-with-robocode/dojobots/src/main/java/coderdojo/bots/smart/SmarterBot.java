package coderdojo.bots.smart;


import coderdojo.bots.robobrain.Brain;
import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.bot.MemoryBot;
import coderdojo.bots.robobrain.filters.*;
import robocode.HitWallEvent;
import robocode.Robot;

import java.awt.*;

/**
 * This is a more intelligent bot that learns from it's encounters with other bots and applies that
 * knowledge in it's strategy.  It uses the "bot-brains" library to add a "Brain" to the bot which
 * helps to store and retrieve remembered information about it's environment.
 *
 * @author RVA Coder Dojo
 */
public class SmarterBot extends MemoryBot {

    // This is our normal routine when not reacting to an "onXYZ" event
    public void run() {
        // Let's give our robot some color.  How about VCU colors?
        // Remember colors are defined by combining RED, GREEN and BLUE
        setBodyColor(new Color(32, 32, 32));  // Dark Gray (almost black)
        setGunColor(new Color (240, 195, 32));  // Golden Yellow
        setRadarColor(new Color(128, 128, 128)); // Lighter shade of Gray
        setBulletColor(new Color(200, 200, 0)); // Yellowish

        Brain brain = getBrain();
        MemoryFilter liveBotFilter = new LiveBotFilter();
        MemoryFilter recentShooterFilter = new MostRecentShootersFilter();
        MemoryFilter recentVictimsFilter = new MostRecentVictimsFilter();
        MemoryFilter recentlyEncounteredFilter = new MostRecentEncounteredFilter();

        out.println("Let's do this!");
        // First scan to see what we can see.
        turnRadarRight(360);

        // Repeat forever (until the battle is over)
        while (true) {

            // See if we have an enemy who shot us recently.
            EnemyInfo enemyInfo = brain.findTop(liveBotFilter, recentShooterFilter);
            if (enemyInfo != null) {
                handleShooter(enemyInfo);
            }
            else {
                // Find a bot that is still alive which we've shot and know the most about it's location so we can target and shoot again.
                enemyInfo = getBrain().findTop(liveBotFilter, recentVictimsFilter, recentlyEncounteredFilter);
                if (enemyInfo != null) {
                    targetRobot(enemyInfo);
                }
                else {
                    // See if we have any bots we've seen recently.  if so target them.
                    enemyInfo = getBrain().findTop(liveBotFilter, recentlyEncounteredFilter);
                    if (enemyInfo != null) {
                        targetRobot(enemyInfo);
                    }
                    else {
                        wanderAround();
                    }
                }

            }
        }
    }

    /**
     * Move away from the wall if we run into one.
     *
     * @param event
     *      The wall hit event.
     */
    @Override
    public void onHitWall(HitWallEvent event) {
        // see if we're closer to the RIGHT hand side.. if so turn left.
        if (getX() > (getBattleFieldWidth() / 2))
            turnLeft(80);
        else
            turnRight(80);
    }


    /**
     * Attempts to stay at a right angle to the enemy and move away from them
     * Will calculate target bearing and fire, then dodge.
     *
     * @param enemyInfo
     *      Information on the enemy who shot us.
     */
    private void handleShooter(EnemyInfo enemyInfo) {

        // Try to turn the shortest distance to be 90 degrees away.
        double bearingAdjust = 0.0;
        if (enemyInfo.getLastBearing() < 0) {
            bearingAdjust = -90 + enemyInfo.getLastBearing();
        }
        else {
            bearingAdjust = enemyInfo.getLastBearing() - 90;
        }

        out.println("Dealing with shooter: " + enemyInfo.getLastBearing());
        double gunAdjust = getHeading() - getGunHeading() + enemyInfo.getLastBearing();
        out.println("   - Aiming at Shooter: " + gunAdjust);
        turnGunRight(gunAdjust);
        out.println("   - Firing at Shooter:");
        fire(3);
        out.println("   - Adjusting Bearing 90deg: " + bearingAdjust);
        turnRight(bearingAdjust);
        out.println("   - Moving away from shooter");
        ahead(100);
        out.println("   - Scanning to update shooter location");
        enemyInfo.setShotMe(null);
    }

    private void targetRobot(EnemyInfo enemyInfo) {

        out.println("Targeting another guy");
        double gunAdjust = getHeading() - getGunHeading() + enemyInfo.getLastBearing();
        turnGunRight(gunAdjust);
        fire(2);
        ahead(65);
        turnRight(20);
        turnRadarRight(360);
    }

    private void wanderAround() {
        out.println("Just wandering");
        turnRadarRight(360);
        ahead(15);
        turnLeft(30);
    }

}
