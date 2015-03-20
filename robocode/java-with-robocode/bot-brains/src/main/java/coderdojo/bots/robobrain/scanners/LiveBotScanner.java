package coderdojo.bots.robobrain.scanners;

import coderdojo.bots.robobrain.EnemyInfo;
import coderdojo.bots.robobrain.MemoryScanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This will scan the list of enemies picking out all of the ones who
 * are still alive.
 *
 * @author RVA Coder Dojo
 */
public class LiveBotScanner extends MemoryScanner {

    @Override
    public List<EnemyInfo> scan(Collection<EnemyInfo> enemies) {
        List<EnemyInfo> liveBots = new ArrayList<EnemyInfo>();

        //////// Enhanced For Loop /////////

        // This is called an "Enhanced For Loop".  It
        // is an easy way to go step by step through a collection of
        // a objects that are all the same type.
        for (EnemyInfo enemyInfo : enemies) {

            // If the bot is not dead, add them to our collection of
            // live bots.
            if (!enemyInfo.isDead()) {
                liveBots.add(enemyInfo);
            }
        }
        return liveBots;
    }
}
