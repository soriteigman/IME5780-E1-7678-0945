package Geometries;

import Primitives.*;

import java.util.List;
import java.util.Objects;

import static Primitives.Util.alignZero;

public class Sphere extends RadialGeometry
{
    Point3D _center;

    public Sphere(double _radius,Point3D _center)
    {
        super(_radius);
        this._center= new Point3D(_center);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null ) return false;
        if (!(o instanceof Sphere)) return  false;
        Sphere other = (Sphere) o;
        return this._center.equals(other._center) && (Util.isZero(this._radius - other._radius));
    }

    public Point3D get_center() {
        return _center;
    }

    @Override
    public Vector getNormal(Point3D p)
    {
        return p.subtract(_center).normalize();
    }

    @Override
    public String toString()
    {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

}