package coderdojo.bots.robobrain.filters;

import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.comparators.MostRecentEnemyEventComparator;
import robocode.BulletHitEvent;
import robocode.ScannedRobotEvent;

import java.util.*;

/**
 * This will filter the list of enemies picking out all of the ones who
 * we have scanned before with the most recent one first.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentlyScannedFilter extends MemoryFilter {

    @Override
    public List<EnemyInfo> filter(Collection<EnemyInfo> enemies) {
        List<EnemyInfo> scanned = new ArrayList<EnemyInfo>();

        for (EnemyInfo enemyInfo : enemies) {

            if (enemyInfo.getScanned() != null) {
                scanned.add(enemyInfo);
            }

            // A comparator provides the logic for making a comparison
            // between two items to figure out which order to sort them in.
            Comparator<EnemyInfo> comparator = new MostRecentEnemyEventComparator(ScannedRobotEvent.class);
            // Sorts the results by "most recent".
            Collections.sort(scanned, comparator);
        }
        return scanned;
    }
}