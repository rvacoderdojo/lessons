import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class WinnerMessage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinnerMessage extends Actor
{
    public WinnerMessage(String message) {
        GreenfootImage textImage = new GreenfootImage(message, 20,  Color.YELLOW, Color.BLACK);
        setImage(textImage);
    }
    
    /**
     * Act - do whatever the WinnerMessage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    }    
}
