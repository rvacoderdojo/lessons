import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DumbTank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DumbTank extends BattleTankBase
{
    protected void dodge() {
        move(5);
    }
    
    protected  void target() {
        // randomly rotate 
        int turnValue = (int)(30.0 - (Math.random() * 60.0));
        turn (turnValue);
    }
    
    protected  Ammunition getAmmo() {
        return new Ammunition(10, "Small_Bullet1.png");        
    }
    
    protected boolean shouldFire() {
        boolean result = false;
        // only fire every 10th move
        if (getCounter() % 10 == 0) {
            result = true;
        }
        
        return result;
    }

}
