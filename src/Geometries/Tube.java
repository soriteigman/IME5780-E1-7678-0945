package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.List;

import static Primitives.Util.isZero;

public class Tube extends RadialGeometry
{
    Ray ray;

    public Tube(double _radius, Ray ray)
    {
        super(_radius);
        this.ray = ray;
    }

    @Override
    public Vector getNormal(Point3D point)
    {
        double t = ray.getDirection().dotProduct(point.subtract(ray.getPoint()));
        Point3D O=ray.getPoint();
        if(!isZero(t));
        O = O.add( ray.getDirection().scale(t));
        return point.subtract(O).normalize();
    }

    public Ray getRay() {
        return ray;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "ray=" + ray +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
