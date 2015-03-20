package coderdojo.bots.robobrain.filters;

import coderdojo.bots.robobrain.EnemyInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This will filter the list of enemies picking out all of the ones who
 * are still alive.
 *
 * @author RVA Coder Dojo
 */
public class LiveBotFilter extends MemoryFilter {

    @Override
    public List<EnemyInfo> filter(Collection<EnemyInfo> enemies) {
        List<EnemyInfo> liveBots = new ArrayList<EnemyInfo>();

        //////// Enhanced For Loop /////////

        // This is called an "Enhanced For Loop".  It
        // is an easy way to go step by step through a collection of
        // a objects that are all the same type.
        // It works for Collections, Lists, Arrays
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
