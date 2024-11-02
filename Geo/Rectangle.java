 
package Geo;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.ArrayList;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;


    // Creates a new rectangle
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color)
    {
        this.upperLeft=new Point(upperLeft.getX(), upperLeft.getY());
        this.width=width;
        this.height=height;
        this. color=color;
        this.top=new Line(upperLeft,new Point(upperLeft.getX()+width, upperLeft.getY()));
        this.bottom=new Line(new Point(upperLeft.getX(), upperLeft.getY()+height),
                new Point(upperLeft.getX()+width, upperLeft.getY()+height));
        this.left= new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY()+height));
        this.right= new Line(new Point(upperLeft.getX()+width, upperLeft.getY()),
                new Point(upperLeft.getX()+width, upperLeft.getY()+height));

    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line)
    {

        java.util.List<Point> list= new ArrayList<>();
        Point p1= line.intersectionWith(top);
        if (p1!=null)
            list.add(p1);
        p1= line.intersectionWith(bottom);
        if (p1!=null)
            list.add(p1);
        p1= line.intersectionWith(left);
        if (p1!=null)
            list.add(p1);
        p1= line.intersectionWith(right);
        if (p1!=null)
            list.add(p1);
        return list;
    }

    // Return the width and height of the rectangle
    public double getWidth()
    {
        return this.width;
    }
    public double getHeight()
    {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft()
    {
        return this.upperLeft;
    }

    public Line getTop()
    {
        return top;
    }

    public Line getBottom()
    {
        return bottom;
    }

    public Line getLeft()
    {
        return left;
    }

    public Line getRight()
    {
        return right;
    }

    public void drawOn(DrawSurface surface)
    {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }

    public Color getColor() {
        return color;
    }
}