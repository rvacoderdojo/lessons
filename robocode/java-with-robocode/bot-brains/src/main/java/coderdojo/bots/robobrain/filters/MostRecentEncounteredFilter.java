package coderdojo.bots.robobrain.filters;

import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.comparators.MostRecentEventComparator;
import robocode.Event;

import java.util.*;

/**
 * This will order the list of enemies from most recently encountered
 * to least.  Using the most recently encountered will help to give
 * greater certainty when using the "bearing" value since it's less
 * likely the bot has moved.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentEncounteredFilter extends MemoryFilter {

    @Override
    public List<EnemyInfo> filter(Collection<EnemyInfo> enemies) {
        List<Event> mostRecentEvents = new ArrayList<Event>();

        // Create a temporary map so we can match the sorted events back to their info.
        Map<Event, EnemyInfo> temporaryMap = new HashMap<Event, EnemyInfo>();

        // Find the most recent event for each enemy and sort them in order from recent to older.
        for (EnemyInfo enemyInfo : enemies) {
            Event event = enemyInfo.getMostRecentEvent();
            mostRecentEvents.add(event);
            temporaryMap.put(event, enemyInfo);
        }

        // A comparator provides the logic for making a comparison
        // between two items to figure out which order to sort them in.
        Comparator<Event> comparator = new MostRecentEventComparator();
        Collections.sort(mostRecentEvents, comparator);

        // Now build our final sorted list of EnemyInfo
        List<EnemyInfo> results = new ArrayList<EnemyInfo>();

        // Now create the final enemy info map sorted by most recently encountered.
        for (Event event : mostRecentEvents) {
            results.add(temporaryMap.get(event));
        }

        return results;
    }
}
