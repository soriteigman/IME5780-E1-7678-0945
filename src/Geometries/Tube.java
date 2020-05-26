package Geometries;

import Primitives.*;
import elements.Material;

import java.util.List;

import static Primitives.Util.alignZero;
import static Primitives.Util.isZero;

public class Tube extends RadialGeometry
{
    Ray ray;

    public Tube(double _radius, Ray ray)
    {
        super(_radius);
        this.ray = ray;
    }

    public Tube(Color emission, Material material, double _radius, Ray ray) {
        super(emission, material, _radius);
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
    public List<GeoPoint> findIntersections(Ray anotherray, double maxDistance) {
        Point3D P = anotherray.getPoint();
        Point3D _point = this.ray.getPoint();

        Vector V = anotherray.getDirection(),
                Va = this.ray.getDirection(),
                DeltaP = new Vector(P.subtract(_point)),
                temp_for_use1, temp_for_use2;

        double V_dot_Va = V.dotProduct(Va),
                DeltaP_dot_Va = DeltaP.dotProduct(Va);

        temp_for_use1 = V.subtract(Va.scale(V_dot_Va));
        temp_for_use2 = DeltaP.subtract(Va.scale(DeltaP_dot_Va));

        double A = temp_for_use1.dotProduct(temp_for_use1);
        double B = 2 * V.subtract(Va.scale(V_dot_Va)).dotProduct(DeltaP.subtract(Va.scale(DeltaP_dot_Va)));
        double C = temp_for_use2.dotProduct(temp_for_use2) - _radius * _radius;
        double desc = alignZero(B * B - 4 * A * C);

        if (desc < 0) {//No solution
            return null;
        }

        double t1 = (-B + Math.sqrt(desc)) / (2 * A),
                t2 = (-B - Math.sqrt(desc)) / (2 * A);

        if (desc == 0) {//One solution
            if (-B / (2 * A) < 0) {
                return null;
            } else {
                return List.of(new GeoPoint(this, P.add(V.scale(-B / (2 * A)))));
            }
        } else if (t1 < 0 && t2 < 0) {
            return null;
        } else if (t1 < 0 && t2 > 0) {
            return List.of(new GeoPoint(this, P.add(V.scale(t2))));
        } else if (t1 > 0 && t2 < 0) {
            return List.of(new GeoPoint(this, P.add(V.scale(t1))));
        } else {
            return List.of(
                    new GeoPoint(this, P.add(V.scale(t1))),
                    new GeoPoint(this, P.add(V.scale(t2)))
            );
        }
    }
}
