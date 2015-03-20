package coderdojo.bots.smart;


import robocode.Robot;

import java.awt.*;

/**
 * This is a more intelligent bot that learns from it's encounters with other bots and applies that
 * knowledge in it's strategy.  It uses the "bot-brains" library to add a "Brain" to the bot which
 * helps to store and retrieve remembered information about it's environment.
 *
 * @author RVA Coder Dojo
 */
public class SmarterBot extends Robot {

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
        }
    }
}
