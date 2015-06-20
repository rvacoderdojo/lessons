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
        
        // See if there are any balloons under the popper
        Balloon balloonActor = (Balloon)getOneObjectAtOffset(0, 0, Balloon.class);
        // If there is a balloon underneath.  That's good! Pop it!
        if (balloonActor != null) 
        {
            balloonActor.popGood();                
        }
    }    
}
