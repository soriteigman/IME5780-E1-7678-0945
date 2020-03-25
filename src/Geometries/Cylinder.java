package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

public class Cylinder extends Tube
{

    double height;

    public Cylinder(double _radius, Ray ray) {
        super(_radius, ray);
    }

    @Override
    public String toString()
    {
        return "Cylinder{" +
                "ray=" + ray +
                ", _radius=" + _radius +
                '}';
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
