import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This tank doesn't have a very intelligent strategy.
 * It moves the same speed all the time and randomly turns.
 * It fires every 10th call to act.
 */
public class DumbTank extends BattleTankBase
{
    public DumbTank() {
        super();
        setComment("Who you calling Dumb?!");
    }
    
    // This tank just moves forward 5 no matter what is going on.
    protected void dodge() {
            move(5);
    }
    
    // Targetting is totally random.  Somewhere between -30 to 30 degrees rotation.
    protected  void target() {
        int turnValue = 30 - Greenfoot.getRandomNumber(60);
        turn (turnValue);
    }
    
    // Shoots red bullets
    protected  Ammunition getAmmo() {
        return new Ammunition(10, "yellow-bullet.png");        
    }
    
    // Fires once every 10th call to act.  
    protected boolean shouldFire() {
        boolean result = false;
        // only fire every 10th move
        if (getCounter() % 10 == 0) {
            result = true;
        }
        
        return result;
    }

}
