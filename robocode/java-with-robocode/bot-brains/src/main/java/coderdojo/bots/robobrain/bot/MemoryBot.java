package coderdojo.bots.robobrain.bot;

import coderdojo.bots.robobrain.Brain;
import robocode.*;

/**
 * This is a simple abstract base bot that has a
 * "Brain" and automatically records events that the
 * brain is capable of understanding.  You need to
 * extend and flesh this bot out to do anything
 * useful.
 *
 * @author RVA Coder Dojo
 */
public abstract class MemoryBot extends Robot {

    private Brain brain;

    @Override
    public void onBulletHit(BulletHitEvent event) {
        getBrain().rememberThis(event);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        getBrain().rememberThis(event);
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {
        getBrain().rememberThis(event);
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        getBrain().rememberThis(event);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        getBrain().rememberThis(event);
    }

    @Override
    public void onDeath(DeathEvent event) { getBrain().resetAll(); }

    /**
     * Get access to the bot's brain to use or update it's
     * "memories"
     *
     * @return A robot brain.
     */
    public Brain getBrain() {

        // If this bot doesn't have a brain, create one for it.
        if (brain == null) {
            brain = new Brain();
        }

        return brain;
    }
}
