 
package Geo;

/**
 * The type Line.
 */
public class Line {

    private Point start;
    private Point end;
    private double m;
    private double b;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - this.start.getX() * this.m;
    }


    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - this.start.getX() * this.m;
    }


    /**
     * Length double.
     *
     * @return the double
     */
    public double length() {
        return this.start.distance(this.end);
    }


    /**
     * Middle point.
     *
     * @return the point
     */
    public Point middle() {
        double x = Math.abs(start.getX() - end.getX()) / 2;
        x += Math.min(start.getX(), end.getX());
        double y = Math.abs(start.getY() - end.getY()) / 2;
        y += Math.min(start.getY(), end.getY());
        Point p = new Point(x, y);
        return p;
    }


    /**
     * Start point.
     *
     * @return the point
     */
    public Point start() {
        return start;
    }


    /**
     * End point.
     *
     * @return the point
     */
    public Point end() {
        return end;
    }


    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        if (Math.abs(this.m) != Math.abs(other.m)) {
            Point p = intersection(other);
            if (this.onLine(p) && other.onLine(p)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            return intersection(other);
        }
        return null;
    }


    private Point intersection(Line other) {
        double x, y;
        if (Double.isInfinite(this.m)) {
            x = this.start.getX();
            y = other.m * x + other.b;
        } else if (Double.isInfinite(other.m)) {
            x = other.start.getX();
            y = this.m * x + this.b;
        } else {
            x = (other.b - this.b) / (this.m - other.m);
            y = this.m * x + this.b;
        }
        return new Point(x, y);
    }


    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> lst = rect.intersectionPoints(this);
        if (lst.isEmpty()) {
            return null;
        }
        Point p1 = lst.get(0), p2;
        for (int i = 1; i < lst.size(); i++) {
            p2 = lst.get(i);
            if (p1.distance(this.start) > p2.distance(this.start)) {
                p1 = p2;
            }
        }
        return p1;
    }


    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other)) {
            return true;
        }
        if (this.m == other.m && this.b == other.b) {
            return true;
        }
        return false;
    }


    /**
     * On line boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean onLine(Point p) {
        double xmin = Math.min(this.start.getX(), this.end.getX());
        double xmax = Math.max(this.start.getX(), this.end.getX());
        double ymin = Math.min(this.start.getY(), this.end.getY());
        double ymax = Math.max(this.start.getY(), this.end.getY());
        if ((p.getX() >= xmin || equals(p.getX(), xmin)) && (p.getX() <= xmax || equals(p.getX(), xmax))
                && (p.getY() >= ymin || equals(p.getY(), ymin)) && (p.getY() <= ymax || equals(p.getY(), ymax))) {
            return true;
        }
        return false;
    }


    private boolean equals(double x, double y) {
        double epsilon = 0.001;
        return Math.abs(x - y) < epsilon;
    }
}
