import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    // Transparent background color for text.
    private static final Color TRANSPARENT = new Color(0,0,0,0);
    
    private String title;
    private int value;
    private GreenfootImage background;
        
    /**
     * Initializes our Counter object.
     */
    public Counter(String titleValue, int startValue) 
    {
        title = titleValue;
        value = startValue;
        background = getImage();  // Grab the background image so we can redraw it.
        
        // Tell the image to update with our value.
        updateImage();
    }
    
    /**
     * Adds 1 to our value
     */
    public void addOne() 
    {
        value++;
    }
    
    /**
     * Takes away 1 from our value.
     */
    public void subtractOne() 
    {
        value--;
    }
    
    /**
     * Adds "howMany" to our value.   You can "subtract" by making this
     * a negative number.  
     * Example:  counter.add(-5);
     */
    public void add(int howMany) 
    {
        value = value + howMany;
    }
    
    public int getValue() 
    {
        return value;
    }
    
    public void act() 
    {
        updateImage();
    }    
    
    /**
     * Draw the updated scoreboard
     */
    private void updateImage()
    {
        // Create a new image using the scoreboard background. 
        GreenfootImage image = new GreenfootImage(background);
        // Add title and the score's value to create the scoreboard text.
        // Black text color, Transparent background color 
        GreenfootImage text = new GreenfootImage(title + value, 22, Color.BLACK, TRANSPARENT);
        
        // This trick will make sure that if the scoreboard text is too wide, the background is 
        // resized to fit.  The "20" adds a little extra space.
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        
        // now combine the text and the scoreboard background together, centering the text background
        image.drawImage(text, (image.getWidth() - text.getWidth()) / 2, 
                              (image.getHeight() - text.getHeight()) / 2);
                              
        // finally display the scoreboard combined image
        setImage(image);
    }
    
    

}
