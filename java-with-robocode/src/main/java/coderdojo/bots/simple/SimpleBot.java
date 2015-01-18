// Simple Robot lives in this package which is in folder src/main/java/coderdojo/bots/simple
package coderdojo.bots.simple;

// Make reference to other packages that we want to use in our class.
import robocode.Robot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

import java.awt.*;

/**
 * A simple Robocode bot that is surprisingly good for it's simplicity. <br/>
 *
 * This helps to demonstrate many of the basic concepts we discussed regarding Java, OO Programming and Robocode.
 *
 * @author RVA Coder Dojo
 */
public class SimpleBot extends Robot {  // Simple robot inherits from it's parent: robocode.Robot

    // This is our normal routine when not reacting to an "onXYZ" event
    public void run() {
        // Let's give our robot some color.  How about VCU colors?
        // Remember colors are defined by combining RED, GREEN and BLUE
        setBodyColor(new Color(32, 32, 32));  // Dark Gray (almost black)
        setGunColor(new Color (240, 195, 32));  // Golden Yellow
        setRadarColor(new Color(128, 128, 128)); // Lighter shade of Gray
        setBulletColor(new Color(200, 200, 0)); // Yellowish

        // Repeat forever (until the battle is over)
        while (true) {
            ahead(100);  // move forward 100 steps
            turnRadarRight(360); // do a full 360 degree sweep of the radar
            fire(1);  // Fire a lower energy bullet
            back(100); // Move backwards 100 steps
            turnRadarRight(360); // do a full 360 degree sweep of the radar
            fire(1);  // Fire a lower energy bullet
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
        turnGunRight(degrees);

        // Fire a more powerful blast (since we're actually aiming this time)
        fire(3);
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
            turnLeft(30);
            turnRight(30);
        }
    }


}
