package coderdojo.bots.robobrain.filters;

import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.comparators.MostRecentEnemyEventComparator;
import robocode.BulletHitEvent;

import java.util.*;

/**
 * This will filter the list of enemies picking out all of the ones
 * who that we have shot successfully with the most recent one first.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentVictimsFilter  extends MemoryFilter {

    @Override
    public List<EnemyInfo> filter(Collection<EnemyInfo> enemies) {
        List<EnemyInfo> victims = new ArrayList<EnemyInfo>();

        for (EnemyInfo enemyInfo : enemies) {

            if (enemyInfo.getShotByMe() != null) {
                victims.add(enemyInfo);
            }

            // A comparator provides the logic for making a comparison
            // between two items to figure out which order to sort them in.
            Comparator<EnemyInfo> comparator = new MostRecentEnemyEventComparator(BulletHitEvent.class);
            // Sorts the results by "most recent".
            Collections.sort(victims, comparator);
        }
        return victims;
    }
}