import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class BattleTankBase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BattleTankBase extends Actor
{
    private int counter;
    private String comment;
    
    public BattleTankBase() {
        counter = 0;
    }
    
    protected abstract void dodge();
    protected abstract void target();
    protected abstract Ammunition getAmmo();
    protected abstract boolean shouldFire();
        
    /**
     * Act - do whatever the BattleTankBase wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        counter++;
        if (counter == Integer.MAX_VALUE) 
            resetCounter();
        target();
        dodge();
                
        if (atWorldEdge()) {
            turn(180);
        }
        if (shouldFire()) 
            fire();
            
        // See if we collided with another tank.  
        handleCollision();            
    }        

    public void fire() {
        Ammunition ammo = getAmmo();
        ammo.setRotation(getRotation());
        getWorld().addObject(ammo, this.getX(), this.getY());
        ammo.move(this.getImage().getWidth() / 2 + 10);
    }

    public void goKaboom() {
        Explosion explosion = new Explosion();
        getWorld().addObject(explosion, getX(), getY());
        getWorld().removeObject(this);
    }
    
    protected void resetCounter() {
        counter =0;
    }
    
    protected int getCounter() {
        return counter;
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
    
    private void handleCollision() {
        List<BattleTankBase> tanks = getIntersectingObjects(BattleTankBase.class);
        if (tanks != null && tanks.size() > 0)  {
            for (BattleTankBase tank : tanks) {
                tank.goKaboom();
            }
            this.goKaboom();
        }
    }
    
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
    
    public String toString() {
        return this.getClass().getName() + " " + getComment();
    }
    
    public void turnTowards (int x, int y) {
        double xDif = x - getX(); 
        double yDif = y - getY();
        double a = Math.atan2(yDif, xDif);
        setRotation((int) Math.toDegrees(a));
    }
}
