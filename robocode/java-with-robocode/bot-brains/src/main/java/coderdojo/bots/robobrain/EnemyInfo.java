package coderdojo.bots.robobrain;

import robocode.*;

/**
 * This object hangs on to the most recent information we have about
 * a Robot we encountered during battle.  It records each type of
 * interesting event that might happen to or from an enemy.
 *
 * @author RVA Coder Dojo
 */
public class EnemyInfo {

    private String name;

    // Records most recent encounter of this event.
    // Variables are named from the perspective of YOUR robot (not the enemy)
    // so "shotByMe" means this Enemy was shot by my Robot.
    private BulletHitEvent shotByMe;
    private HitByBulletEvent shotMe;
    private HitRobotEvent crashed;
    private RobotDeathEvent death;
    private ScannedRobotEvent scanned;
    private DeathEvent killedMe;

    /**
     * Create a new Enemy Info instance and
     * remember it's name.
     *
     * Note: you cannot change the name once this object is created
     * this is to keep from confusing the robot brain since the name
     * will be the key for our HashMap in the brain.
     *
     * @param name The name of the robot.
     */
    public EnemyInfo (String name) {
        this.name = name;
    }

    /**
     * We would like an easy way to figure out if the robot is dead
     * so we can ignore it during battle in the Robot Brain.
     *
     * @return true if the robot is dead, false if it isn't.
     */
    public boolean isDead() {

        // If we captured a "RobotDeathEvent" (variable name "death") then
        // this robot we're tracking is dead.
        if (death != null) {
            return true;
        }
        else {
            return false;
        }

        // Note: cool coder's trick.  Logical statements are boolean by nature, so we can skip the
        // IF/ELSE and just return the result of our comparison by writing:
        // return (death != null);
    }

    /**
     * This will reset all the information we have about this enemy except "killedMe".
     * That allows our robot to "hold a grudge" against this enemy.
     * You might wish to call this at the end of each round.
     */
    public void reset() {
        shotByMe = null;
        shotMe = null;
        crashed = null;
        death = null;
        scanned = null;
    }

    /**
     * This will help to find our most recent encounter with this enemy.
     * All events have a "time" field.  The most recent "time" is the
     * number that is the largest.
     *
     * @return Our most recent encounter with this bot.
     */
    public Event getMostRecentEvent() {

        // This is a little Java magic.
        // Event is an abstract class (meaning you must create a child of it before it's
        // constructor can be called), so this creates a subclass that extends "Event"
        // and then creates a new instance of that extended class.
        Event mostRecentEvent = new Event() {};
        mostRecentEvent.setTime(0);  // set the time into the far past.

        mostRecentEvent = findMostRecent(mostRecentEvent, shotByMe);
        mostRecentEvent = findMostRecent(mostRecentEvent, shotMe);
        mostRecentEvent = findMostRecent(mostRecentEvent, crashed);
        mostRecentEvent = findMostRecent(mostRecentEvent, death);
        mostRecentEvent = findMostRecent(mostRecentEvent, scanned);

        return mostRecentEvent;
    }

    /**
     * Compares our current "most recent" event to another one and returns whichever is the most recent
     * of the two.  If "comparisonEvent" is null, then "mostRecentEvent" is returned.
     *
     * @param mostRecentEvent
     *      This is the most RecentEvent we've seen so far.  This should never be null.
     * @param comparisonEvent
     *      This is the event we want to compare.  This could be null because the event
     *      may not have happened before with this bot.
     *
     * @return The most recent of the two events being compared.
     */
    private Event findMostRecent(Event mostRecentEvent, Event comparisonEvent) {

        // If the comparisonEvent time is not a null value, and it's more recent, replace
        // the prior "mostRecentEvent" value with the "comparisonEvent"
        if (comparisonEvent != null && comparisonEvent.getTime() > mostRecentEvent.getTime())
            mostRecentEvent = comparisonEvent;

        return mostRecentEvent;
    }

    ///// Getters and Setters for Robot attributes /////
    public String getName() {
        return name;
    }

    public BulletHitEvent getShotByMe() {
        return shotByMe;
    }

    public void setShotByMe(BulletHitEvent shotByMe) {
        this.shotByMe = shotByMe;
    }

    public HitByBulletEvent getShotMe() {
        return shotMe;
    }

    public void setShotMe(HitByBulletEvent shotMe) {
        this.shotMe = shotMe;
    }

    public HitRobotEvent getCrashed() {
        return crashed;
    }

    public void setCrashed(HitRobotEvent crashed) {
        this.crashed = crashed;
    }

    public RobotDeathEvent getDeath() {
        return death;
    }

    public void setDeath(RobotDeathEvent death) {
        this.death = death;
    }

    public ScannedRobotEvent getScanned() {
        return scanned;
    }

    public void setScanned(ScannedRobotEvent scanned) {
        this.scanned = scanned;
    }

    public DeathEvent getKilledMe() {
        return killedMe;
    }

    public void setKilledMe(DeathEvent killedMe) {
        this.killedMe = killedMe;
    }

}
