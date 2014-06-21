import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * This will display who won the game and their celebratory comment.
 */
public class WinnerMessage extends Actor
{
    public WinnerMessage(BattleTankBase tank) {
        String message = "Winner is " + tank.getClass().getName() + ": " + tank.getComment();
        GreenfootImage textImage = new GreenfootImage(message, 20,  Color.YELLOW, Color.BLACK);
        setImage(textImage);
    }
    
}
