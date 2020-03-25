package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

public class Tube extends RadialGeometry
{
    Ray ray;

    public Tube(double _radius, Ray ray)
    {
        super(_radius);
        this.ray = ray;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
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
}
