package coderdojo.bots.robobrain;

import robocode.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This will be the main brain for storing and filtering information about the
 * robot's world.
 *
 * @author RVA Coder Dojo
 */
public class Brain {

    /////// Data Structure: Maps ///////

    // This will be the memory for the brain.
    // It is a "Map" which has both a collection of "keys" and "values".
    // The "keys" are how you lookup something in a Map.
    // For our Brain we'll use the enemy robot's name as the "key"
    // and everything we know about that enemy stored in "EnemyInfo" will
    // be the value.
    private HashMap<String, EnemyInfo> memory;

    /**
     * Our constructor will initialize the brain and setup the memory.
     */
    public Brain() {
        memory = new HashMap<String, EnemyInfo>();
    }

    ////// Polymorphism //////

    // "Polymorphism" sounds like a really big word but it's a really important concept in
    // Object Oriented Languages (like Java).
    // When a class is "polymorphic" that means that it appears in different forms.
    // For example:
    // - If you picture a "Person" in your mind you'd probably see a "Head", "Body", "Arms", "Legs".
    // - If you picture your brother, sister, mom, dad, aunt, uncle grandmother or grandfather
    //   they are all "People" but each one has their own distinct forms and behaviors.
    // - They all can "speak()" but how they "speak()"  is different.
    //
    // In Object Oriented Programming Languages, we can create Polymorphic classes where a generalized
    // class definition (like the "Person" above) can be implemented different ways by it's chilren
    // (like our "brother", "sister", "father" or "mother" are all different).
    //
    // In the example code below we have the general concept of a "MemoryScanner" which can "scan()"
    // the values stored in memory and return a list of matching "EnemyInfo" objects.  We can implement
    // different "strategies" for our "MemoryScanner" which return a different list of EnemyInfo objects.
    // So while all of our "MemoryScanners" look the same, in practice, each actual scanner implementation
    // is slightly different.

    /**
     * A method to find Enemies that match the criteria defined in the
     * by stacking together the various memory scanners.  You can think
     * of this like going through a sequence of filters.
     * The first memory scanner is run, and the results of that are fed into
     * the next one.<br/>
     * <br/>
     * Example:<br/>
     * find (new MostRecentShooterScanner(), new LiveBotScanner());<br/>
     * Will first find all the bots who've shot us then filter it down to those left alive.
     *
     * @param scanners
     *      One or more MemoryScanner implementations.
     *
     * @return a list of matching EnemyInfo bots.
     */
    /////// Varargs ///////
    // This uses Varargs which allows the method to take MULTIPLE arguments of the same type.
    // The Vararg method parameter must always be the last one in the list and is denoted
    // by the "..." that follows the class name
    public List<EnemyInfo> find(MemoryScanner... scanners) {

        // First start out with the full list of bots.
        List<EnemyInfo> results = new ArrayList<EnemyInfo>(memory.values());

        //////// Enhanced For Loop /////////

        // This is called an "Enhanced For Loop".  It
        // is an easy way to go step by step through a collection of
        // a objects that are all the same type.
        // It works for Collections, Lists, Arrays

        // Now apply each of our scanners passed in via the Vararg to the list.
        for (MemoryScanner scanner : scanners)  {
            results = scanner.scan(results);
        }

        return results;
    }

    ////// Overloaded methods //////

    // Overloaded methods are when you have multiple methods with the same
    // name but different input values.
    // we have a lot of "rememberThis()" methods below, but each one takes
    // a different event type and works a little differently inside.
    // this is what Overloading is in java.
    /**
     * Records that we recently shot a robot.
     *
     * @param event
     *      The event details of the bot we shot.
     */
    public void rememberThis(BulletHitEvent event) {
        EnemyInfo enemyInfo = getEnemyInfo(event.getName());
        enemyInfo.setShotByMe(event);
    }

    /**
     * Records that we were recently shot by a robot.
     *
     * @param event
     *      The event details of the bot that shot us.
     */
    public void rememberThis(HitByBulletEvent event) {
        EnemyInfo enemyInfo = getEnemyInfo(event.getName());
        enemyInfo.setShotMe(event);
    }

    /**
     * Records that we recently crashed into a robot.
     *
     * @param event
     *      The event details of the bot that we crashed into.
     */
    public void rememberThis(HitRobotEvent event) {
        EnemyInfo enemyInfo = getEnemyInfo(event.getName());
        enemyInfo.setCrashed(event);
    }

    /**
     * Records that we killed a bot.
     *
     * @param event
     *      The event details of the bot that we killed.
     */
    public void rememberThis(RobotDeathEvent event) {
        EnemyInfo enemyInfo = getEnemyInfo(event.getName());
        enemyInfo.setDeath(event);
    }


    /**
     * Records that we scanned a robot
     *
     * @param event
     *      The event details of the bot that we scanned.
     */
    public void rememberThis(ScannedRobotEvent event) {
        EnemyInfo enemyInfo = getEnemyInfo(event.getName());
        enemyInfo.setScanned(event);
    }

    /**
     * Resets event data on all known enemies.
     * This should probably be called at the end of each
     * round of battle.
     */
    public void resetAll() {

        //////// Enhanced For Loop /////////
        for (EnemyInfo enemyInfo : memory.values()) {
            enemyInfo.reset();
        }
    }

    /**
     * Returns all the enemies we have seen over the course of battle.
     *
     * @return all known enemies.
     */
    public List<EnemyInfo> getAllEnemies() {

        return  new ArrayList(memory.values());
    }

    /**
     * This method will try to locate the named robot in memory.
     * If the bot is not found, a new EnemyInfo class is created with
     * the name, and added to the Brain's "memory".
     *
     * @param name
     *      The name of the bot to find.
     *
     * @return An EnemyInfo object mapped to the enemy bot by their name.
     */
    public EnemyInfo getEnemyInfo(String name) {
        EnemyInfo enemyInfo = memory.get(name);

        // Check to see if this is the first time we've seen this enemy.
        if (enemyInfo == null) {
            // If so, create a new EnemyInfo object and map it to this
            // enemy by name.
            enemyInfo = new EnemyInfo(name);

            // now add it to our brain's "memory" using the
            // "name" as the "key" and the "enemyInfo" object
            // as the thing we want to remember.
            memory.put(name, enemyInfo);
        }

        // Return what we know so far about the enemy.
        return enemyInfo;
    }

}
