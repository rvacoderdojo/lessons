import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChargingTank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChargingTank extends BattleTankBase
{
    private BattleTankBase currentTarget = null;
    
    public ChargingTank() {
        super();
        setComment("Hulk Smash!!!! Ready! Aim! CHAAARGE!!!!");
    }
    
    protected void dodge() {
        // If we've locked in to a target charge rapidly at it.
        if (currentTarget != null) {
            move(25);
        }
        // If no target just keep puttering around.
        else {
            move(5);
        }
    }
    
    // Looks for closest tank.  
    // If that tank is close enough will "acquire" the target, turn towards it and charge at it.
    protected void target() {        
        // If we have no target, or are too far away from our prior target, pick a new one.
        
        if (currentTarget == null || calculateDistance(this, currentTarget) > 200.0) {
            
            // Current target is too far away.. forget about it.
            currentTarget = null;
            
            // Where is the closest tank.
            BattleTankBase closestTank = findClosestTank();        
            
            // Make sure there is a tank on the board.
            if (closestTank != null) {
                // figure out how far away we are from it.
                double distance = calculateDistance(this, closestTank);
                // if tanks are within 200 pixels of each other.. CHARGE!
                if (distance < 200.0) { 
                    currentTarget = closestTank;
                }
            }
            else // Too far away so no target to acquire yet or we lost the one we did acquire.
                currentTarget = null;
        }
       
        // If there's no tank acquired, just randomly rotate to point in a new direction.
        if (currentTarget == null)  {
            int turnDirection = (int)((Math.random() * 10.0)  );           
            turn(turnDirection);
        }
        else {
            turnTowards(currentTarget.getX(), currentTarget.getX());
        }
    }
    
    // This tank doesn't shoot.  It "smashes" like the incredible hulk
    protected Ammunition getAmmo() {
        return null;
    }
    
    // this tank batters... not fires.
    protected boolean shouldFire() { 
        return false;
    }
}
