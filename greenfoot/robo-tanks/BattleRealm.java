import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class BattleRealm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleRealm extends World
{

    /**
     * Constructor for objects of class BattleRealm.
     * 
     */
    public BattleRealm()
    {    
        super(800, 600, 1);         
        randomPositionActor(new DumbTank());
        randomPositionActor(new StationaryTank());
        randomPositionActor(new ChargingTank()); 
    }
    
    /**
     * Finds a random x/y position for an actor
     */
    private void randomPositionActor(Actor actor){
        // pick a number between 0 and 80% of the width across.
        int xpos = (int)(Math.random() * (getWidth() * 0.8));
        
        // If the xpos would be off screen... scoot the actor over a little.
        if (xpos < actor.getImage().getWidth())
            xpos = actor.getImage().getWidth() + 5; // offset from the edge
        
        // pic a number between 0 and 80% of the height of the screen
        int ypos = (int)(Math.random() * (getHeight() * 0.8));
        
        // If the ypos would be off screen... scoot the actor down a little.
        if (ypos < actor.getImage().getHeight())
            ypos = actor.getImage().getHeight() + 5; // offset from the edge        

        addObject(actor, xpos, ypos);
    }
    
    public void act() {
        try {
            Thread.sleep(100);
            List<BattleTankBase> tanks = this.getObjects(BattleTankBase.class);
            if (tanks.size() == 1) {
                BattleTankBase winner = tanks.get(0);
                Actor winnerMsg = new WinnerMessage(winner.getComment());
                addObject(winnerMsg, 50 + (winnerMsg.getImage().getWidth() / 2), 50);                
                Greenfoot.stop();
            }
            else
                if (tanks.size() == 0) {
                    Greenfoot.stop();
                }
        } 
        catch (Exception ex) {}
    }
    
}
