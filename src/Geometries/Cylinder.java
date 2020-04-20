package Geometries;

import Primitives.*;

import java.util.List;

import static Primitives.Util.alignZero;
import static Primitives.Util.isZero;

/**
 * Class Cylinder is the class representing a cylinder
 *
 * @author Esther Burack and Sara Teigman
 */
public class Cylinder extends Tube
{

    double height;

    public Cylinder(double _radius, Ray ray, double h)
    {
        super(_radius, ray);
        height=h;
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
    public Vector getNormal(Point3D point)
    {
        Plane plane = new Plane(ray.getPoint(), ray.getDirection());
        Vector v1 = ray.getPoint().subtract(point);
        if((v1.dotProduct(ray.getDirection()))==0) //the vectors are orthogonal
        {
            return (ray.getDirection().scale(-1)).normalize();
        }
        Point3D p1 = ray.getPoint().add(ray.getDirection().normalized().scale(height));
        v1 = p1.subtract(point);
        if((v1.dotProduct(ray.getDirection()))==0) //the vectors are orthogonal
        {
            return (ray.getDirection()).normalize();
        }
        Vector v = new Vector(point);
        Point3D p = new Point3D(point);
        double t = v.dotProduct(new Vector(p));
        Point3D o = new Point3D(v.scale(t).getHead());
        Vector n = (p.subtract(o)).normalize();
        return n;    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
