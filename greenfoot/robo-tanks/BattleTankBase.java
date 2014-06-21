import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleTankBase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class BattleTankBase extends Actor
{
    private int counter;
    
    public BattleTankBase() {
        counter = 0;
    }
    
    /**
     * Act - do whatever the BattleTankBase wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        counter++;
        if (counter == Integer.MAX_VALUE) 
            resetCounter();
        dodge();
        if (atWorldEdge()) {
            turn(getRotation() + 180);
        }
        target();
        if (shouldFire()) 
            fire();
    }    

    public void fire() {
        Ammunition ammo = getAmmo();
        ammo.setRotation(getRotation());
        getWorld().addObject(ammo, this.getX(), this.getY());
        ammo.move(this.getImage().getWidth() / 2 + 10);
        //move(10);  // move the bullet out of the way of the tank the fired it.
    }

    protected abstract void dodge();
    protected abstract void target();
    protected abstract Ammunition getAmmo();
    protected abstract boolean shouldFire();
    
    public void goKaboom() {
        this.setImage("explosion.png");
        Greenfoot.playSound("Explosion.wav");
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
    public boolean atWorldEdge()
    {
        if(getX() < 20 || getX() > getWorld().getWidth() - 20)
            return true;
        if(getY() < 20 || getY() > getWorld().getHeight() - 20)
            return true;
        else
            return false;
    }
    
    public BattleTank findClosestTank() {
        List<BattleTank> tanks = getWorld().getObjects(BattleTank.class);
        BattleTank closestTank = null;
        
        for (BattleTank tank : tanks) {
            
        }
    }
}
