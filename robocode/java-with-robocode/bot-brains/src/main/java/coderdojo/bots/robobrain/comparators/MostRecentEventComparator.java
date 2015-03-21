package coderdojo.bots.robobrain.comparators;

import robocode.Event;

import java.util.Comparator;

/**
 * Compares two events, and orders them with most recent first.  If
 * a "null" is present, then that is considered a "later" event since it has
 * never happened before and has no time associated with it.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentEventComparator implements Comparator<Event> {

    // Comparators can be confusing.
    // a value < 0 means that enemy1 comes before enemy2
    // a value of 0 means enemy1 and enemy2 are equal
    // a value of > 0 means enemy2 comes before enemy1
    @Override
    public int compare (Event event1, Event event2) {
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
