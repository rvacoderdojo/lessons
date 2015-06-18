import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Popper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Popper extends Actor
{    
    /**
     * Act - do whatever the Popper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Figure out where the mouse is on the screen
        MouseInfo info = Greenfoot.getMouseInfo();
        
        if (info != null) 
        {
            // Move the popper to that location.
            setLocation(info.getX(), info.getY());
        }
        
        Balloon balloonActor = (Balloon)getOneObjectAtOffset(0, 0, Balloon.class);
        if (balloonActor != null) 
        {
            balloonActor.popGood();                
        }
    }    
}
