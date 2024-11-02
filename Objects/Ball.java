 

package Objects;
import Geo.Line;
import Geo.Point;
import Geo.Velocity;
import Managment.CollisionInfo;
import Managment.Game;
import Managment.GameEnvironment;
import biuoop.DrawSurface;
import Managment.Sprite;
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;
    // constructor
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game)
    {
        this.center=new Point(center.getX(), center.getY());
        this.radius=r;
        this.color=color;
        this.velocity= new Velocity(0,0);
        this.game= game;
    }

    // accessors
    public double getX(){
        return this.center.getX();
    }
    public double getY(){
        return this.center.getY();
    }
    public int getSize()
    {
        return this.radius;
    }
    public java.awt.Color getColor()
    {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface)
    {
        surface.setColor(this.color);
        surface.fillCircle((int)this.center.getX(), (int)this.center.getY(), this.radius);
    }

    @Override
    public void timePassed()
    {
        moveOneStep();
    }

    public void setVelocity(Velocity v){
       this.velocity= new Velocity(v.getDx(), v.getDy());
    }
    public void setVelocity(double dx, double dy){
        this.velocity= new Velocity(dx, dy);
    }
    public Velocity getVelocity(){
        return this.velocity;
    }

    public void moveOneStep()
    {
        Line newLine= new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo info= this.game.getClosestCollision(newLine);
        if(info== null)
            this.center=newLine.end();
        else
        {
            this.velocity=info.collisionObject().hit(info.collisionPoint(), this, this.velocity);
            double x, y;
            if (this.center.getX()> info.collisionPoint().getX())
            {
                x=info.collisionPoint().getX()+1;
            }
            else {
                x= info. collisionPoint().getX() -1;
            }
            if (this.center.getY()> info.collisionPoint().getY())
            {
                y=info.collisionPoint().getY()+1;
            }
            else {
                y= info. collisionPoint().getY() -1;
            }
            this.center= new Point(x,y);
        }

    }
    public void addToGame(Game game)
    {
        game.addSprite(this);
    }
    public void removeFromGame(Game game)
    {
        game.removeSprite(this);
    }


}