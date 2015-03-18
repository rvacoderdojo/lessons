package coderdojo.bots.simple;

import robocode.*;

import java.awt.*;

/**
 * A simple Robocode bot that is surprisingly good for it's simplicity. <br/>
 *
 * This illustrates the differences in behavior between a basic Robot and
 * an AdvancedRobot.  While both are coded with the same strategy, you'll
 * notice that the AdvancedRobot does not become paralyzed during battle.
 *
 * @author RVA Coder Dojo
 */
public class SimpleAdvancedBot extends AdvancedRobot {


    // This is our normal routine when not reacting to an "onXYZ" event
    public void run() {
        // Let's give our robot some color.  How about VCU colors?
        // Remember colors are defined by combining RED, GREEN and BLUE
        setBodyColor(new Color(240, 195, 32));  // Golden Yellow
        setGunColor(new Color(32, 32, 32));  // Dark Gray (almost black)
        setRadarColor(new Color(128, 128, 128)); // Lighter shade of Gray
        setBulletColor(new Color(200, 200, 0)); // Yellowish

        // Repeat forever (until the battle is over)
        while (true) {
            setTurnRadarRight(360);
            setFire(1);
            setAhead(100);
            out.println("Moved ahead and spun my radar");
            waitFor(new MoveCompleteCondition(this));
            setTurnRadarRight(360);
            setFire(1);
            out.println("Moved BACK and spun my radar");
            back(100);
            waitFor(new MoveCompleteCondition(this));
        }
    }

    // Define behavior for our bot when it detects another bot
    public void onScannedRobot (ScannedRobotEvent scannedRobot) {

        // Calculate how to aim the gun: adjust for the tank heading and gun heading
        // - First calculate how much we have to turn to align the gun with the front of the tank.
        //    gun_adjustment = tank_heading - gun_heading
        // - then adjust for the bearing
        //    turn_degress = gun_adjustment + enemy_bearing
        double degrees = getHeading() - getGunHeading() + scannedRobot.getBearing();

        // now adjust the gun based on our calculation
        setTurnGunRight(degrees);

        // Fire a more powerful blast (since we're actually aiming this time)
        waitFor(new GunTurnCompleteCondition(this));
        setFire(3);
        out.println("Fired on scanned robot: " + scannedRobot.getName());
    }

    // Our robot likes to gloat when it kills another robot!
    public void onRobotDeath(RobotDeathEvent robotDeathEvent) {
        out.println("Take that " + robotDeathEvent.getName());
    }

    // Do a little victory wiggle when you win
    public void onWin (WinEvent winEvent) {
        // Brag
        out.println("I am VICTORIOUS!");

        // Do a little wiggle (15 times)
        for (int count = 0; count < 15; count++) {
            setTurnLeft(30);
            waitFor(new TurnCompleteCondition(this));
            setTurnRight(30);
            waitFor(new TurnCompleteCondition(this));
        }
    }
}
