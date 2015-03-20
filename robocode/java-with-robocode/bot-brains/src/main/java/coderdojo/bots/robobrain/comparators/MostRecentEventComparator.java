package coderdojo.bots.robobrain.comparators;

import coderdojo.bots.robobrain.EnemyInfo;
import robocode.*;

import java.util.Comparator;

/**
 * Compares the most recent event between two events handling nulls if they
 * arise (nulls are "less recent" than a non-null event.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentEventComparator<T extends Class<Event>> implements Comparator<EnemyInfo> {

    private T eventType;

    public MostRecentEventComparator(T eventType) {
        this.eventType = eventType;
    }

    @Override
    public int compare(EnemyInfo enemy1, EnemyInfo enemy2) {

        if (BulletHitEvent.class.isInstance(eventType))
            return compareEvents(enemy1.getShotByMe(), enemy2.getShotByMe());
        if (HitByBulletEvent.class.isInstance(eventType))
            return compareEvents(enemy1.getShotMe(), enemy2.getShotMe());
        if (HitRobotEvent.class.isInstance(eventType))
            return compareEvents(enemy1.getCrashed(), enemy2.getCrashed());
        if (RobotDeathEvent.class.isInstance(eventType))
            return compareEvents(enemy1.getDeath(), enemy2.getDeath());
        if (ScannedRobotEvent.class.isInstance(eventType))
            return compareEvents(enemy1.getScanned(), enemy2.getScanned());

        return 0;
    }

    // Comparators can be confusing.
    // a value < 0 means that enemy1 comes before enemy2
    // a value of 0 means enemy1 and enemy2 are equal
    // a value of > 0 means enemy2 comes before enemy1
    public int compareEvents (Event event1, Event event2) {
        int result = 0;

        // If event1 is null and event2 isn't, event 1 is less recent
        // (because it never happened)
        if (event1 == null && event2 != null)
            result = -1;
        else
            if (event1 != null && event2 == null)
                result = 1;
            else
                if (event1 == null && event2 == null)
                    result = 0;
                else
                    result = (int)(event2.getTime() - event1.getTime());

        return result;

    }
}
