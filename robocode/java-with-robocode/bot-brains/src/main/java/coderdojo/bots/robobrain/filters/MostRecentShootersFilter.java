package coderdojo.bots.robobrain.filters;

import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.comparators.MostRecentEnemyEventComparator;
import robocode.HitByBulletEvent;

import java.util.*;

/**
 * This will filter the list of enemies picking out all of the ones who
 * have shot our bot with the most recent one first.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentShootersFilter extends MemoryFilter {

    @Override
    public List<EnemyInfo> filter(Collection<EnemyInfo> enemies) {
        List<EnemyInfo> shooters = new ArrayList<EnemyInfo>();

        for (EnemyInfo enemyInfo : enemies) {

            // Filter out only those who have shot us before.
            if (enemyInfo.getShotMe() != null) {
                shooters.add(enemyInfo);
            }
        }

        // A comparator provides the logic for making a comparison
        // between two items to figure out which order to sort them in.
        Comparator<EnemyInfo> comparator = new MostRecentEnemyEventComparator(HitByBulletEvent.class);
        // Sorts the shooters by "most recent".
        Collections.sort(shooters, comparator);

        return shooters;
    }
}
