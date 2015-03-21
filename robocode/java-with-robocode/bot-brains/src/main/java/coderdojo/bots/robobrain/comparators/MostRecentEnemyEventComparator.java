package coderdojo.bots.robobrain.comparators;

import coderdojo.bots.robobrain.EnemyInfo;
import robocode.*;

import java.util.Comparator;

/**
 * Finds the most recent event of a specific event type when comparing 2 enemies.
 * handling nulls if they arise (nulls are "less recent" than a non-null event).
 *
 * @author RVA Coder Dojo
 */
public class MostRecentEnemyEventComparator<T extends Class<Event>> implements Comparator<EnemyInfo> {

    private T eventType;
    private MostRecentEventComparator eventComparator;

    public MostRecentEnemyEventComparator(T eventType) {
        this.eventType = eventType;
        eventComparator = new MostRecentEventComparator();
    }

    /**
     * This method is largely concerned with choosing the right 2 events to compare and then delegating the
     * comparison to the MostRecentEventComparator.
     *
     * @param enemy1
     *      One enemy
     * @param enemy2
     *      Another enemy
     * @return the proper ordering when comparing the specified event type between the two enemies (based on timestamp)
     */
    @Override
    public int compare(EnemyInfo enemy1, EnemyInfo enemy2) {

        // Find the right event type and use it's data for comparison
        if (BulletHitEvent.class.isInstance(eventType))
            return eventComparator.compare(enemy1.getShotByMe(), enemy2.getShotByMe());

        if (HitByBulletEvent.class.isInstance(eventType))
            return eventComparator.compare(enemy1.getShotMe(), enemy2.getShotMe());

        if (HitRobotEvent.class.isInstance(eventType))
            return eventComparator.compare(enemy1.getCrashed(), enemy2.getCrashed());

        if (RobotDeathEvent.class.isInstance(eventType))
            return eventComparator.compare(enemy1.getDeath(), enemy2.getDeath());

        if (ScannedRobotEvent.class.isInstance(eventType))
            return eventComparator.compare(enemy1.getScanned(), enemy2.getScanned());

        // If this is an event we don't track then all things are considered equal.
        return 0;
    }

}
