 


package Objects;
import Geo.Point;
import Geo.Rectangle;
import Geo.Velocity;
import Litsener.HitListener;
import Litsener.HitNotifier;
import Managment.Collidable;
import Managment.Game;
import Managment.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable, Sprite, HitNotifier
{
    private List <HitListener> listener;
    private Rectangle rect;
    public Block(Point upperLeft, double width, double height, java.awt.Color color)
    {
        this.listener= new ArrayList<HitListener>();
        this.rect=new Rectangle(upperLeft, width, height,color);
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Point collisionPoint,Ball hitter, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if(this.rect.getTop().onLine(collisionPoint) || this.rect.getBottom().onLine(collisionPoint))
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy()*-1);
        if(this.rect.getRight().onLine(collisionPoint) || this.rect.getLeft().onLine(collisionPoint))
            return new Velocity(currentVelocity.getDx()*-1, currentVelocity.getDy());
        return currentVelocity;
    }

    private void notifyHit(Ball hitter) {
            // Make a copy of the hitListeners before iterating over them.
            List<HitListener> listeners = new ArrayList<HitListener>(this.listener);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
    }

    @Override
    public void drawOn(DrawSurface d)
    {
        this.rect.drawOn(d);
    }

    @Override
    public void timePassed(){}
    public void addToGame(Game game)
    {
        game.addCollidable(this);
        game.addSprite(this);
    }
    public void removeFromGame(Game game)
    {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    public void changeLocation(Point upperLeft)
    {
        this.rect= new Rectangle(upperLeft, this.rect.getWidth(), this.rect.getHeight(),this.rect.getColor());
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.listener.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.listener.remove(hl);

    }
}
