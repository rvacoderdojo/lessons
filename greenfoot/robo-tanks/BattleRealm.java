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
     * Constructor.  Creates our battle realm world and puts tanks in it.
     */
    public BattleRealm()
    {    
        // make our screen 800 pixels wide, by 600 pixels tall.
        super(800, 600, 1);     
        
        // Sets how fast to call the "act()" method.  bigger numbers are faster.
        Greenfoot.setSpeed(40);         
        
        // Set up one each of or tanks.
        randomPositionActor(new DumbTank());
        randomPositionActor(new StationaryTank());
        randomPositionActor(new ChargingTank()); 
    }
    
    /**
     * Finds a random x/y position for an actor
     */
    private void randomPositionActor(Actor actor){
        // pick a number between 0 and the play area width
        int xpos = Greenfoot.getRandomNumber(getWidth());
        
        // If the xpos would be off screen... scoot the actor over a little.
        if (xpos < actor.getImage().getWidth())
            xpos = xpos + actor.getImage().getWidth() + 5; // offset from the left edge
            
        if (xpos > getWidth())
            xpos = xpos - actor.getImage().getWidth() - 5; // offset from the right edge.
            
        // pick a number between 0 and the play area height
        int ypos = Greenfoot.getRandomNumber(getHeight());
        
        // If the xpos would be off screen... scoot the actor over a little.
        if (ypos < actor.getImage().getHeight())
            ypos = ypos + actor.getImage().getHeight() + 5; // offset from the left edge
            
        if (ypos > getHeight())
            ypos = ypos - actor.getImage().getHeight() - 5; // offset from the right edge.

        addObject(actor, xpos, ypos);
    }
    
    public void act() {        
        // Find all the tanks left in the world
        List<BattleTankBase> tanks = this.getObjects(BattleTankBase.class);
        
        // If there is only 1 tank left we have a winner.  Announce their winning message. 
        if (tanks.size() == 1) {
            BattleTankBase winner = tanks.get(0);
            Actor winnerMsg = new WinnerMessage(winner);
            addObject(winnerMsg, 50 + (winnerMsg.getImage().getWidth() / 2), 50);                
            // Stop the animations.
            Greenfoot.stop();
        }
        else
            // If no tanks are left then stop the animations.
            if (tanks.size() == 0) {
                Greenfoot.stop();
            }
    }
    
}
