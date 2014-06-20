import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A balloon object that rises or falls depending on the 
 * amount of noise in the room.
 * 
 * @author Macon Pegram
 */
public class Balloon extends Actor
{
    // Which direction and how fast the ballon is moving (negative values go up, positive go down).
    private double gravity;
    
    // Current Y position of the balloon as a floating point number to help with rounding.
    private double yPos;

    // The lowest point the balloon can go before it's on the bottom of the screen. 
    private int maxYPos; 
    
    public Balloon () {
        // Gravity starts as a positive number so the balloon drops.
        gravity = 0.01;
        
    }
    
    /**
     * Act - do whatever the Balloon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Can't capture yPos until the image is drawn the first time.
        if (yPos == 0) { 
            yPos = this.getY();
            // find the bottom of the world, then substract 1/2 the height of the image 
            // that's because the X and Y position of the image is based on the center of
            // the image.  
            maxYPos = this.getWorld().getHeight() - (getImage().getHeight() / 2);
        } 

        // See if we hit the bottom, bounce up
        if (yPos > maxYPos)
            bounceUp();
           
        // Use the current loudness level to adjust gravity.
        adjustGravity(Greenfoot.getMicLevel());
        
        // Apply gravity to the current yPos (move up or down)
        yPos += gravity;
        
        // Draw the balloon at the new location after gravity has been applied.
        setLocation(getX(), (int)Math.round(yPos));
        
        // See if we hit the top
        if (yPos < (getImage().getHeight() / 2))
            pop(); // pop the balloon
        
    } 
    
    /**
     * Reset gravity and make sure the balloon doesn't get stuck to the bottom.
     */
    public void bounceUp() {
        // multiply by a negative number to turn the gravity value to a negative (move up)
        // decrease the power of gravity a little bit (90% of what it was) so we don't bounce back
        // as high as we came from.  
        gravity = gravity * -0.9;  
        
        // Reposition the balloon 1 pixel up to keep from getting stuck to the bottom.
        yPos = maxYPos - 1; 
    }
    
    /**
     * Based on the loudness level adjust gravity.
     * If no sound is present the gravity increases.
     * If there is noise, decrease gravity a little bit proportional to how loud the noise is.
     */
    private void adjustGravity(int loudness) {
        
        // If there is sound, and we haven't already reached maximum anti-gravity (-3.0)
        if (loudness > 0 && gravity > -3.0) 
            // Apply some "anti-gravity" to slow down the balloon or move it upwards.
            // A scaled version of the loudness value is substracted from gravity.
            gravity -= (0.01 * (loudness / 10.0));
        else
            // If there was no measurable noise, we add gravity up to the value of 3.0.
            if (gravity < 3.0)
                gravity += 0.01;
    }
    
    /**
     * Pop this balloon.
     */
    private void pop() 
    {
        Greenfoot.playSound("pop.wav");
        getWorld().removeObject(this);
    }
    
}
