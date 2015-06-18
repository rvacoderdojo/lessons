package com.bot.samplememory;

import coderdojo.bots.robobrain.Brain;
import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.bot.MemoryBot;
import coderdojo.bots.robobrain.filters.LiveBotFilter;
import coderdojo.bots.robobrain.filters.MemoryFilter;
import coderdojo.bots.robobrain.filters.MostRecentEncounteredFilter;

public class SampleMemoryBot extends MemoryBot {

    public void run() {

        Brain brain = getBrain();
        MemoryFilter liveBotFilter = new LiveBotFilter();
        MemoryFilter recentlyEncounteredFilter = new MostRecentEncounteredFilter();

        while (true) {
            // See if we have any bots we've seen recently. if so target them.
            EnemyInfo enemyInfo = brain.findTop(liveBotFilter,
                    recentlyEncounteredFilter);
            if (enemyInfo != null) {
                targetRobot(enemyInfo);
            } else {
                wanderAround();
            }
        }
    }

    private void targetRobot(EnemyInfo enemyInfo) {

        double gunAdjust = getHeading() - getGunHeading()
                + enemyInfo.getLastBearing();
        turnGunRight(gunAdjust);
        fire(2);
        wanderAround();
    }

    private void wanderAround() {
        int distance = pickRandomNumber(150);
        if (heads()) {
            ahead(distance);
        } else {
            back(distance);
        }

        int randomDirectionChoice = pickRandomNumber(100);
        if (randomDirectionChoice < 30) {
            int angle = pickRandomNumber(90);
            turnRight(angle);
        } else {
            if (randomDirectionChoice < 60) {
                int angle = pickRandomNumber(90);
                turnLeft(angle);
            }
        }
        // Note: Scan after moving or your bearings will be off.
        turnRadarRight(360);
    }

    private int pickRandomNumber(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    private boolean heads() {
        return (pickRandomNumber(100) >= 50);
    }

}
