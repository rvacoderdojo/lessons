import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private GreenfootImage explosionImage;
    private GreenfootSound explosionSound;
    private int imageScale = 15;
    
    public Explosion() {
        explosionImage = new GreenfootImage("explosion.png");
        explosionImage.scale(imageScale, imageScale);
        explosionSound = new GreenfootSound("Explosion.wav");
        setImage(explosionImage);
    }
    
    protected void addedToWorld(World world) {
        explosionSound.play();
    }
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (explosionSound.isPlaying()) {            
            imageScale += 10;
            explosionImage.scale(imageScale, imageScale);
        } 
        else
            getWorld().removeObject(this);                
    }   
    
    
}
