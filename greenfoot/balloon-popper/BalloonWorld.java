import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BalloonWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BalloonWorld extends World
{
    private Counter scoreboard;
    private Counter lives;
    private Popper popper;
    private long delay;
    private long lastBalloonTime;
    
    public BalloonWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        // Create our scoreboard, and position it in the upper left.
        scoreboard = new Counter("Score: ", 0);
        addObject(scoreboard, 60, 20);
        
        // Create a Life counter, Start with 3 lives
        lives = new Counter("Lives: ", 3);
        addObject(lives, 720, 20);
        
        // Add our popper to the middle of the screen. 
        popper = new Popper();
        addObject(popper, getWidth() / 2, getHeight() / 2);
        
        // Setup timing for adding next balloon
        delay = 1500;  // Add a balloon every 1.5 seconds (100 milliseconds)
        lastBalloonTime = 0; // Exact time when the last balloon was created (0 = never)
    }    
    
    public void act() 
    {
        // See if it is time add another balloon
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBalloonTime > delay)
        {
            addBalloon();  // Add a new balloon
    
            // Now update the time value for when we created a new balloon
            lastBalloonTime = currentTime;
            // Decrease the delay between new balloons by 20 milliseconds... getting faster.
            if (delay > 150)
            {
                delay = delay - 20;
            }
        }
        // If we ran out of lives, stop the game
        if (lives.getValue() < 1)
        {
            Greenfoot.stop();
        }
    }
    
    private void addBalloon() 
    {    
        // This puts a balloon in a random location at the bottom of the screen.
        addObject(new Balloon(), Greenfoot.getRandomNumber(getWidth()), getHeight());
    }
    
    public void scorePoint()
    {
        scoreboard.addOne();
    }
    
    public void loseLife()
    {
        lives.subtractOne();
    }
    
}

