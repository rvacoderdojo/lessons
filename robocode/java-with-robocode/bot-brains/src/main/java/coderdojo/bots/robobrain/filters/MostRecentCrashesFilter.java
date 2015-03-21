package coderdojo.bots.robobrain.filters;

import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.comparators.MostRecentEnemyEventComparator;
import robocode.HitRobotEvent;

import java.util.*;

/**
 * This will filter the list of enemies picking out all of the ones
 * who have collided with our bot with the most recent one first.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentCrashesFilter  extends MemoryFilter {

    @Override
    public List<EnemyInfo> filter(Collection<EnemyInfo> enemies) {
        List<EnemyInfo> crashers = new ArrayList<EnemyInfo>();

        for (EnemyInfo enemyInfo : enemies) {
            // Filter out only those that have crashed before.
            if (enemyInfo.getCrashed() != null) {
                crashers.add(enemyInfo);
            }
        }

        // A comparator provides the logic for making a comparison
        // between two items to figure out which order to sort them in.
        Comparator<EnemyInfo> comparator = new MostRecentEnemyEventComparator(HitRobotEvent.class);
        // Sorts the results by "most recent".
        Collections.sort(crashers, comparator);
        return crashers;
    }
}
