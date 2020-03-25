package Primitives;

import java.util.Objects;
/**
 * Class Vector is the basic class representing a vector of Euclidean geometry in Cartesian
 *  * 3-Dimensional coordinate system.
 *  * @author Sara Teigman and Esther Burack
 * */
public class Vector
{
    Point3D head;

    public Vector(Coordinate _x, Coordinate _y, Coordinate _z)
    {
        Coordinate zero=new Coordinate(0.0);

            if (_x.equals(zero) && _y.equals(zero) && _z.equals(zero))
                throw new IllegalArgumentException("cannot create zero vector");
            head=new Point3D(new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
    }

    public Vector(double _x, double _y, double _z)
    {
        if (head.equals( Point3D.ZERO))
            throw new IllegalArgumentException("cannot create zero vector");
        head=new Point3D(new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
    }

    public Vector(Point3D head)
    {
        if (head.equals( Point3D.ZERO))
            throw new IllegalArgumentException("cannot create zero vector");
        this.head = head;
    }

    public Vector(Vector v)
    {
        this.head = v.head;
    }

    public Vector(Point3D p1, Point3D p2) { this(p1.subtract(p2)); }

    /**
     * function to subtract vectors
     * @param secondVector vector to be subtracted
     * @return new vector
     */
    public Vector subtract(Vector secondVector) { return this.head.subtract(secondVector.head); }

    /**
     * adds vectors
     * @param v vector to be added
     * @return new vector
     */
    public Vector add(Vector v) { return new Vector(this.head.add(v)); }

    /**
     * multiplies a scalar with a vector
     * @param v scalar to be multiplied
     * @return vector result
     */
    public Vector scale(double v) { return new Vector(new Point3D(new Coordinate(v*head._x.get()),new Coordinate(v*head._y.get()),new Coordinate(v*head._z.get()))); }

    /**
     * dot multiplies vectors to create a scalar
     * @param v vector to multiply by
     * @return scalar result
     */
    public double dotProduct(Vector v)
    {
        return (this.head._x._coord * v.head._x._coord +
                this.head._y._coord * v.head._y._coord +
                this.head._z._coord * v.head._z._coord);
    }

    /**
     * cross product multiplacation between two vectors
     * @param v vector to multiply by
     * @return vector result
     */
    public Vector crossProduct(Vector v)
    {
        double w1 = this.head._y._coord * v.head._z._coord - this.head._z._coord * v.head._y._coord;
        double w2 = this.head._z._coord * v.head._x._coord - this.head._x._coord * v.head._z._coord;
        double w3 = this.head._x._coord * v.head._y._coord - this.head._y._coord * v.head._x._coord;
        return new Vector(new Point3D(w1, w2, w3));
    }

    /**
     *
     * @return length of a vector squared
     */
    public double lengthSquared()
    {
        double xx = this.head._x._coord * this.head._x._coord;
        double yy = this.head._y._coord * this.head._y._coord;
        double zz = this.head._z._coord * this.head._z._coord;
        return xx + yy + zz;
    }

    /**
     *
     * @return length of a vector
     */
    public double length() { return Math.sqrt(lengthSquared()); }

    /**
     *
     * @return normalizes vector that was sent
     */
    public Vector normalize()
    {
        double x = this.head._x._coord;
        double y = this.head._y._coord;
        double z = this.head._z._coord;
        double length = this.length();
        if (length == 0)
            throw new ArithmeticException("divide by Zero");
        this.head._x = new Coordinate(x / length);
        this.head._y = new Coordinate(y / length);
        this.head._z = new Coordinate(z / length);
        return this;
    }

    /**
     *
     * @return returns a new vector thats the normalization of sent vector
     */
    public Vector normalized()
    {
        Vector vector = new Vector(this);
        vector.normalize();
        return vector;
    }

    public Point3D getHead()
    { return head; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }

    @Override
    public String toString()
    {
        return "Vector{" +
                "head=" + head +
                '}';
    }

}





