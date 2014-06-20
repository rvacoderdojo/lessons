import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the "world" that the balloon app lives in. 
 * 
 * @author Macon Pegram
 * @version 06/19/2014
 */
public class ClapperWorld extends World
{
    Counter loudnessMeter;
    
    /**
     * Constructor for objects of class ClapperWorld.
     * 
     */
    public ClapperWorld()
    {    
        // Set the screen size to 800 wide and 600 high
        super(800, 600, 1); 
        
        // Initialize a Counter with the label "Loudness:" and put it on screen near the top.
        loudnessMeter = new Counter("Loudness:");
        addObject(loudnessMeter, 60, 20);
        
        // Add a balloon in the center of the screen.
        addObject(new Balloon(), 400, 300);
    }
    
    public void act() {
        // Refresh the on screen loudness meter with the current noise level.
        updateLoudnessMeter();
        
        // If the mouse is clicked add a balloon
        if (Greenfoot.mouseClicked(this)) 
            addBalloon();
    }
    
    /**
     * Reads how loud things are using the computer's mic 
     * and updates the Loudness meter on the screen.
     */
    private void updateLoudnessMeter() {
        loudnessMeter.setValue(Greenfoot.getMicLevel());
    }
    
    /**
     * Adds a balloon wherever the mouse is currently.
     */
    private void addBalloon() {
        // Find out where the mouse is pointing
        MouseInfo info = Greenfoot.getMouseInfo();
        
        // Put a balloon at the current mouse location.
        addObject(new Balloon(), info.getX(), info.getY());
    }
    
}
