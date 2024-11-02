 

package Managment;
import java.util.ArrayList;
import biuoop.DrawSurface;

public class SpriteCollection {
    private java.util.List<Sprite> spriteList;
    public SpriteCollection()
    {
        spriteList= new ArrayList<>();
    }
    public void addSprite(Sprite s)
    {
        spriteList.add(s);
    }
    public void removeSprite(Sprite s)
    {
        spriteList.remove(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed()
    {
        for(int i=0; i< spriteList.size(); i++)
        {
            spriteList.get(i).timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d)
    {
        for(int i=0; i< spriteList.size(); i++)
        {
            spriteList.get(i).drawOn(d);
        }
    }
}