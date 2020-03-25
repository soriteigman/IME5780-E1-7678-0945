package Primitives;

import java.util.Objects;
import java.lang.Math;
/**
 * Class Point3D is the basic class representing a point of Euclidean geometry in Cartesian
 *  *  * 3-Dimensional coordinate system.
 *  *  * @author Sara Teigman and Esther Burack
 *  * */
public class Point3D
{
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;
    /**
     * zero point3D to compare with other point3Ds
     */
    public final static Point3D ZERO = new Point3D(0.0,0.0,0.0);


    public Point3D(Point3D p)
    {
        _x=p._x;
        _y=p._y;
        _z=p._z;
    }

    public Point3D(double _x, double _y, double _z)
    {
        this(new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
    }

    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z)
    {
        this._x=_x;
        this._y = _y;
        this._z = _z;
    }


    public Coordinate get_x() {
        return new Coordinate(_x);
    }

    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    public Coordinate get_z() {
        return new Coordinate(_z);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }


    @Override
    public String toString() {
        return "("+_x+","+_y+","+_z+")";
    }

    /**
     * creates a vector by subtracting two points
     * @param secondPoint point to subtract
     * @return vector result
     */
    public Vector subtract(Point3D secondPoint)
    {
        return new Vector(new Point3D(this._x.get()-secondPoint._x.get(),this._y.get()-secondPoint._y.get(),this._z.get()-secondPoint._z.get()));
    }

    /**
     * creates a point from adding a vector to a point
     * @param v vector to add
     * @return point result
     */
    public Point3D add(Vector v)
    {
        return new Point3D(this._x.get()+v.head._x.get(),this._y.get()+v.head._y.get(),this._z.get()+v.head._z.get());
    }

    /**
     * distance between two points squared
     * @param p point to compare
     * @return returned number
     */
    public double distanceSquared(Point3D p)
    {
        return ((this._x.get()+p._x.get())*(this._x.get()+p._x.get()))+((this._y.get()+p._y.get())*(this._y.get()+p._y.get()))+((this._z.get()+p._z.get())*(this._z.get()+p._z.get()));
    }

    /**
     * distance between two points
     * @param p point to compare
     * @return returns the distance result
     */
    public double distance(Point3D p)
    {
        return Math.sqrt(distanceSquared(p));
    }
}
