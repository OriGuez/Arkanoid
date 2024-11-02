 
package Managment;
import Geo.Point;
import Geo.Rectangle;
import Geo.Velocity;
import Objects.Ball;

public interface Collidable {
    // Return the "collision shape" of the object.

    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    Velocity hit(Point collisionPoint, Ball hitter, Velocity currentVelocity);
}