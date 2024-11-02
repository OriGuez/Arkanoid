 

package Managment;
import Geo.Line;
import Geo.Point;
import java.util.ArrayList;
public class GameEnvironment {
    private java.util.ArrayList<Collidable> list;
    public GameEnvironment()
    {
        this.list= new ArrayList<>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c)
    {
        list.add(c);
    }
    public void removeCollidable(Collidable c) {
        list.remove(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory)
    {
        java.util.List<Point> points= new ArrayList<>();
        java.util.List<Integer> index= new ArrayList<>();

        for (int i=0; i< list.size(); i++)
        {
            Point p= trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle());
            if (p!=null) {
                points.add(p);
                index.add(i);
            }
        }
        if(points.isEmpty())
            return null;
        double min=100000000;
        int j=0;
        Point p=new Point(0,0);
        for (int i=0; i< points.size(); i++)
        {
            if (points.get(i).distance(trajectory.start()) < min)
            {
                min = points.get(i).distance(trajectory.start());
                p = points.get(i);
                j = index.get(i);
            }
        }
        return new CollisionInfo(p, list.get(j));
    }

}