package coderdojo.bots.robobrain.comparators;

import coderdojo.bots.robobrain.EnemyInfo;

import java.util.Comparator;

/**
 * Comparator to sort enemies based on which one most recently shot
 * our bot.  If there is no "EnemyInfo.shotMe()" info available, it
 * will be sorted towards the end of the list.
 *
 * @author RVA Coder Dojo
 */
public class MostRecentShooterComparator extends MostRecentEvent implements Comparator<EnemyInfo> {

    @Override
    public int compare(EnemyInfo enemy1, EnemyInfo enemy2) {

        return compareEvents(enemy1.getShotMe(), enemy2.getShotMe());
    }
}
