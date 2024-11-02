 

package Managment;
import Geo.Point;

public class CollisionInfo {
    // the point at which the collision occurs.
    private Point collisionPoint;
    private Collidable collisionObject;
    public CollisionInfo(Point collisionPoint, Collidable collisionObject)
    {
        this.collisionPoint=collisionPoint;
        this.collisionObject=collisionObject;
    }
    public Point collisionPoint()
    {
        return this.collisionPoint;

    }

    // the collidable object involved in the collision.
    public Collidable collisionObject()
    {
        return this.collisionObject;
    }
}