import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Balloon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Balloon extends Actor
{
    private int speed;
    
    public Balloon()
    {
        // Randomly pick a speed for the balloon to float to the top.
        speed = Greenfoot.getRandomNumber(5) + 1;        
    }
    
    public void act() 
    {
        // Move UP the screen using the speed value.
        setLocation(getX(), getY() - speed);
        
        // See if we hit the top (this is bad).  Pop the balloon
        if (getY() < getImage().getHeight() / 2) 
        {
            popBad();
        }
    }    
    
    public void popBad()
    {
        BalloonWorld world = (BalloonWorld)getWorld();
        world.loseLife();               
        pop("uhoh.wav");
    }
    
    public void popGood() 
    {
        BalloonWorld world = (BalloonWorld)getWorld();
        world.scorePoint();               
        pop("pop.wav");
    }
    
    public void pop(String soundName) 
    {
        Greenfoot.playSound(soundName);
        getWorld().removeObject(this);
    }
}
