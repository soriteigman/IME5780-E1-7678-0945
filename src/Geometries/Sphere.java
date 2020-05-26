package Geometries;

import Primitives.*;
import elements.Material;

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

    public Sphere(Color emission, Material material, double _radius, Point3D _center) {
        super(emission, material, _radius);
        this._center = _center;
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

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, ray.getTargetPoint(_radius)));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(new GeoPoint(this, ray.getTargetPoint(t2))); //P1 , P2
        if (t1 > 0)
            return List.of(new GeoPoint(this,ray.getTargetPoint(t1)));
        else
            return List.of(new GeoPoint(this,ray.getTargetPoint(t2)));
    }

}