package coderdojo.bots.robobrain;

import robocode.Event;

import java.util.Collection;
import java.util.List;

/**
 * This is a partial implementation of a MemoryFilter (abstract class)
 * to search through a list of Enemies and find a smaller
 * matching subset based on the rules defined by each subclass
 * when implementing the "filter()" method.   This class provides
 * useful helper methods that will be common across scanners.
 *
 * @author RVA Coder Dojo
 */
public abstract class MemoryFilter {

    ////// Abstract Classes and Methods  /////

    // Abstract classes are classes which have left out the details for
    // implementing one or more methods.  You cannot create an instance
    // of an Abstract class... you must create a child class which
    // provides the details the Abstract class left out and then create
    // an instance of that child class.
    //
    // An abstract method like the one below is a method that defines
    // the name, input and output values, but does not provide the
    // logic for how that method should work.
    // These are the methods that the "children" of Abstract Classes must
    // implement.
    public abstract List<EnemyInfo> filter(Collection<EnemyInfo> enemies);


    /**
     * Checks to see if the event is notNull.
     *
     * @param event
     *      The event to check
     *
     * @return true if the event is not null, false otherwise.
     */
    public boolean notNull(Event event) {
        return event != null;
    }
}
