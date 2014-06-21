import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ammunition can be assigned a unique image and speed.
 */
public class Ammunition extends Actor
{
    private int velocity;
    
    // Create the ammo with a unique image and speed for the bullet.
    public Ammunition(int velocity, String imageName) {
        setImage(imageName);
        this.velocity = velocity;
    }
    
    /**
     * Moves the ammo at the defined velocity.
     * Checks to see if:
     * - We hit a tank... If so call goKaboom() on the tank to blow it up and remove the ammo
     * - If we are near the edge, remove the ammo.
     */
    public void act() 
    {
        move(velocity);
        
        BattleTankBase deadTank = (BattleTankBase)this.getOneIntersectingObject(BattleTankBase.class);
        if (deadTank != null) {
            deadTank.goKaboom();
            getWorld().removeObject(this);
        }
        else {
            // See if we're close to the edge.
            if(getX() < 20 || getX() > getWorld().getWidth() - 20 
               || getY() < 20 || getY() > getWorld().getHeight() - 20)
                getWorld().removeObject(this);
        }
    }
    
}
