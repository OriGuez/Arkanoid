 


package Geo;
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;
    // constructor
    public Velocity(double dx, double dy){
        this.dx=dx;
        this.dy=dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p, Point topLeft, Point bottomRight, int r){
      if (p.getY()-r<= topLeft.getY() || p.getY()+r>= bottomRight.getY())
      {
          this.dy *=-1;
      }
        if (p.getX()-r<= topLeft.getX() || p.getX()+r>= bottomRight.getX())
        {
            this.dx *=-1;
        }
        if (p.getY()+dy<= topLeft.getY() || p.getY()+dy>= bottomRight.getY())
        {
            this.dy *=-1;
        }
        if (p.getX()+dx<= topLeft.getX() || p.getX()+dx>= bottomRight.getX())
        {
            this.dx *=-1;
        }

//        if (p.getY()-dy<= topLeft.getY() || p.getY()+dy>= bottomRight.getY())
//        {
//            this.dy *=-1;
//        }
//        if (p.getX()-dx<= topLeft.getX() || p.getX()+dx>= bottomRight.getX())
//        {
//            this.dx *=-1;
//        }

      double newX= p.getX()+this.dx;
      double newY= p.getY()+this.dy;
      return new Point(newX, newY);

    }
    public double getDx(){
        return this.dx;
    }
    public double getDy(){
        return this.dy;
    }

        public static Velocity fromAngleAndSpeed(double angle, double speed) {
            double dx = speed* Math.cos(Math.toRadians(angle));
            double dy = -speed* Math.sin(Math.toRadians(angle));
            return new Velocity(dx, dy);

    }
    public Point applyToPoint(Point p)
    {
        Point newP= new Point(p.getX()+dx, p.getY()+dy);
        return newP;
    }
}