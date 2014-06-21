import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * This is the basic definition of a battle tank.  
 * 
 * All the common things a tank can do are defined here.  These include:
 * - Basic action: target, dodge, and fire if necessary.
 * - fire: fires the weapon
 * - goKaboom: blows up the tank.
 * - Other helper methods like: findClosestTank, calculateDistance, and atWorldsEdge 
 *      - These helper methods are inherited by each of our special tanks.
 *      
 * "Abstract" methods like "dodge", "target", "getAmmo" and "shouldFire" are what makes each tank sub-class
 * unique and special.  Declaring a method "abstract" means that one of the sub-classes must implement it.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BattleTankBase extends Actor
{
    // The counter is used to help us understand how many steps a tank has taken.
    private int counter;
    // This is the tank's celebration message if it wins.
    private String comment;
    
    // Create the basic tank and set our counter to 0.
    public BattleTankBase() {
        counter = 0;
    }
    
    // All of these abstract method need to be created by Tank subclasses. 
    // They let the tank have it's own strategy for dodging, targetting, and what it's weapons look like and do
    protected abstract void dodge();
    protected abstract void target();
    protected abstract Ammunition getAmmo();
    protected abstract boolean shouldFire();
        
    /**
     * All tanks go through the same basic loop
     * - Increment the counter
     * - Dodge (move)
     * - Target and see
     * - Make sure we're not at the edge of the world... if so turn 180 degrees around.
     * - See if it's time to fire.  If so fire our weapon.
     * - See if we ran in to another tank.
     */
    public void act() 
    {
        counter++;
        // If counter is as large as it can get, reset it to 0.
        if (counter == Integer.MAX_VALUE) 
            resetCounter();
        dodge();
        
        target();
        
        if (atWorldEdge()) {
            turn(180);
        }
        
        if (shouldFire()) 
            fire();
            
        // See if we collided with another tank.  
        handleCollision();            
    }        

    /**
     * When it's time to fire, we get a new piece of ammunition, rotate it the same direction we're facing
     * add it to the world, and start it running.
     */
    public void fire() {
        // Retrieve the tank's ammo.
        Ammunition ammo = getAmmo();
        // Point the ammo in the direction the tank is facing.
        ammo.setRotation(getRotation());
        // Put the ammo in the world.
        getWorld().addObject(ammo, this.getX(), this.getY());
        
        // move the ammo a little ways away from the tank so we don't shoot ourselves.
        ammo.move(this.getImage().getWidth() / 2 + 10);
    }

    /**
     * This handles blowing up the tank.
     * The tank gets replaced by an Explosion actor.
     */
    public void goKaboom() {
        Explosion explosion = new Explosion();
        getWorld().addObject(explosion, getX(), getY());
        getWorld().removeObject(this);
    }
    
    /**
     * Test if we are close to one of the edges of the world. Return true if we are.
     */
    private boolean atWorldEdge()
    {
        if(getX() < 40 || getX() > getWorld().getWidth() - 40)
            return true;
        if(getY() < 40 || getY() > getWorld().getHeight() - 40)
            return true;
        else
            return false;
    }
    
    /**
     * See if this tank has collided with another tank.  If so explode!
     */
    private void handleCollision() {
        List<BattleTankBase> tanks = getIntersectingObjects(BattleTankBase.class);
        if (tanks != null && tanks.size() > 0)  {
            for (BattleTankBase tank : tanks) {
                tank.goKaboom();
            }
            this.goKaboom();
        }
    }
    
    /**
     * Find another tank, other than ourselves, that is nearby.  If we find several return the one
     * that is closest to us.
     */
    protected BattleTankBase findClosestTank() {
        List<BattleTankBase> tanks = getWorld().getObjects(BattleTankBase.class);
        BattleTankBase closestTank = null;
        
        double shortestDistance = getWorld().getWidth(); // make this a ridiculus value.
        
        for (BattleTankBase tank : tanks) {
            
            // Make sure we're not matching against ourselves.
            if (tank != this) {
                double distance = calculateDistance(this, tank);
                
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    closestTank = tank;
                }
            }
        }
        return closestTank;
    }
    
    /**
     * Don't get lost in the math here, but this is the Pythagorean (the square of the hypotenuse 
     * is equal to the sum of the squares of the two sides).  We take the Square Root at the end to
     * convert from length squared to just length.
     *
     * It can be used to figure out the distance
     * between 2 points.
     */
    protected double calculateDistance(Actor actor1, Actor actor2) {
        double x1 = actor1.getX();
        double x2 = actor2.getX();
        double y1 = actor1.getY();
        double y2 = actor2.getY();
        
        double xDif = x1 - x2; 
        double yDif = y1 - y2;
        
        return Math.sqrt((xDif * xDif) + (yDif * yDif));
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
        return comment;
    }
            
    protected void resetCounter() {
        counter =0;
    }
    
    protected int getCounter() {
        return counter;
    }
    
}
