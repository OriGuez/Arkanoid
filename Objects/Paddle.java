 

package Objects;
import Managment.Collidable;
import Managment.Game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Geo.Point;
import Geo.Rectangle;
import Geo.Velocity;
import Managment.Sprite;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color, biuoop.KeyboardSensor key)
    {
        this.block= new Block(upperLeft, width, height,color);
        this.keyboard=key;
    }

    public void moveLeft()
    {
        Point p;
        Rectangle rect= this.block.getCollisionRectangle();
        if(rect.getUpperLeft().getX()-5>=10)
            p= new Point(rect.getUpperLeft().getX()-5, rect.getUpperLeft().getY());
        else
            p= new Point(10, rect.getUpperLeft().getY());
        this.block.changeLocation(p);
    }
    public void moveRight()
    {
        Point p;
        Rectangle rect= this.block.getCollisionRectangle();
        if(rect.getUpperLeft().getX()+ rect.getWidth()+5<=790)
            p= new Point(rect.getUpperLeft().getX()+5, rect.getUpperLeft().getY());
        else
            p= new Point(790- rect.getWidth(), rect.getUpperLeft().getY());
        this.block.changeLocation(p);
    }

    @Override
    public void drawOn(DrawSurface d)
    {
        this.block.drawOn(d);
    }

    public void timePassed()
    {
        if( keyboard.isPressed(KeyboardSensor.LEFT_KEY))
            moveLeft();
        if( keyboard.isPressed(KeyboardSensor.RIGHT_KEY))
            moveRight();
    }

    public Rectangle getCollisionRectangle()
    {
        return this.block.getCollisionRectangle();
    }
    public Velocity hit(Point collisionPoint,Ball hitter,  Velocity currentVelocity) {
        if(this.block.getCollisionRectangle().getBottom().onLine(collisionPoint))
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy()*-1);
        if(this.block.getCollisionRectangle().getRight().onLine(collisionPoint) ||
                this.block.getCollisionRectangle().getLeft().onLine(collisionPoint))
            return new Velocity(currentVelocity.getDx()*-1, currentVelocity.getDy());
        if(this.block.getCollisionRectangle().getTop().onLine(collisionPoint)) {
            double w = block.getCollisionRectangle().getWidth() / 5;
            double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
            if (block.getCollisionRectangle().getUpperLeft().getX() + w >= collisionPoint.getX()) {
                return Velocity.fromAngleAndSpeed(300, speed);
            }
            if (block.getCollisionRectangle().getUpperLeft().getX() + 2 * w >= collisionPoint.getX()) {
                return Velocity.fromAngleAndSpeed(330, speed);
            }
            if (block.getCollisionRectangle().getUpperLeft().getX() + 3 * w >= collisionPoint.getX()) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            }
            if (block.getCollisionRectangle().getUpperLeft().getX() + 4 * w >= collisionPoint.getX()) {
                return Velocity.fromAngleAndSpeed(30, speed);
            }
            if (block.getCollisionRectangle().getUpperLeft().getX() + 5 * w >= collisionPoint.getX()) {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        }

        return currentVelocity;
    }



    public void addToGame(Game g)
    {
        g.addSprite(this);
        g.addCollidable(this);
    }
}