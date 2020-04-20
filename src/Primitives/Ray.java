package Primitives;

import java.awt.*;
import java.util.Objects;

import static Primitives.Util.isZero;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 *  *  * 3-Dimensional coordinate system.
 *  *  * @author Sara Teigman and Esther Burack
 *  * */
public class Ray
{
    Point3D _poo;
    Vector direction;

    public Ray(Point3D _poo, Vector direction)
    {

        this._poo = _poo;
        this.direction = direction.normalized();//normalizes vector before setting as direction
    }

    public Point3D getPoint()
    {
        return new Point3D(_poo);
    }

    /**
     * @author
     * @param length
     * @return new Point3D
     */
    public Point3D getTargetPoint(double length) {
        return isZero(length ) ? _poo : _poo.add(direction.scale(length));
    }


    public Vector getDirection()
    {
        return new Vector(direction.head);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return _poo.equals(ray._poo) &&
                direction.equals(ray.direction);
    }

    @Override
    public String toString()
    {
        return "Ray{" +
                "Point3D=" + _poo +
                ", Vector=" + direction + '}';
    }

    public Point3D getPoint(double t)
    {
        if(isZero(t))
            return _poo;
        return _poo.add(direction.scale(t));
    }

}
