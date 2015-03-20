package coderdojo.bots.robobrain.scanners;

import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.MemoryScanner;
import coderdojo.bots.robobrain.comparators.MostRecentShooterComparator;

import java.util.*;

/**
 * This will scan the list of enemies picking out all of the ones who
 * are still alive.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentShootersScanner  extends MemoryScanner {

    @Override
    public List<EnemyInfo> scan(Collection<EnemyInfo> enemies) {
        List<EnemyInfo> shooters = new ArrayList<EnemyInfo>();

        for (EnemyInfo enemyInfo : enemies) {

            if (enemyInfo.getShotMe() != null) {
                shooters.add(enemyInfo);
            }

            // A comparator provides the logic for making a comparison
            // between two items to figure out which order to sort them in.
            Comparator<EnemyInfo> comparator = new MostRecentShooterComparator();
            // Sorts the shooters by "most recent".
            Collections.sort(shooters, comparator);
        }
        return shooters;
    }
}
