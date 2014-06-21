import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This tank just sits there and fires straight ahead
 */
public class StationaryTank extends BattleTankBase
{
    public StationaryTank() {
        super();
        setComment("I didn't even have to move!");
    }
    
    // Doesn't dodge because he's stationary
    protected void dodge() { }
    
    // Doesn't look for targets because he doesn't move
    protected void target() { }

    // Shoots blue fast moving ammo
    protected Ammunition getAmmo() {
        return new Ammunition(15, "red-bullet.png");
    }
    
    // Fires every 5th time the act method gets called. 
    protected boolean shouldFire() { 
        if (getCounter() % 5 == 0) {
            return true;
        }
        else
            return false;
    }
}
