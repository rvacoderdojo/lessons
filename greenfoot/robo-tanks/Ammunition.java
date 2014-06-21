import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ammunition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ammunition extends Actor
{
    private int velocity;
    
    public Ammunition(int velocity, String imageName) {
        setImage(imageName);
        this.velocity = velocity;
    }
    
    /**
     * Act - do whatever the Ammunition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
