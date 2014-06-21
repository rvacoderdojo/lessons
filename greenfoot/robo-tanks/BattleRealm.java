import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleRealm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleRealm extends World
{

    /**
     * Constructor for objects of class BattleRealm.
     * 
     */
    public BattleRealm()
    {    
        super(800, 600, 1);         
        addObject(new DumbTank(), 400, 300);
    }
    
    public void act() {
        try {
            Thread.sleep(100);
        } 
        catch (Exception ex) {}
    }
    
}
