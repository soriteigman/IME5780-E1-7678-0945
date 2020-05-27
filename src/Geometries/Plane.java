package Geometries;

import Primitives.*;
import elements.Material;

import java.util.List;

import static Primitives.Util.alignZero;
import static Primitives.Util.isZero;

public class Plane extends Geometry
{

    Point3D _p;
    Primitives.Vector _normal;

    public Plane(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, material);
        //from FlatGeometry
        this._p = null;

        _p = new Point3D(p1);

        Vector U = new Vector(p1, p2);
        Vector V = new Vector(p1, p3);

        Vector N = U.crossProduct(V);
        N.normalize();

        _normal = N;
    }

    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, p1, p2, p3);
    }

    public Plane(Point3D _p, Vector _normal) {
        super(Color.BLACK, new Material(0, 0, 0));

        this._p = new Point3D(_p);
        this._normal = new Vector(_normal);
    }


    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    public Vector getNormal() {
        return getNormal(null);
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        Vector p0Q;
        try {
            p0Q = _p.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct(ray.getDirection());
        if (isZero(nv)) { // ray is parallel to the plane - no intersections
            return null;
        }
        double t = alignZero(_normal.dotProduct(p0Q) / nv);
        double tdist = alignZero(maxDistance - t);

        if ((t <= 0) || (tdist <= 0)) {
            return null;
        } else {
            return List.of(new GeoPoint(this, ray.getTargetPoint(t)));
        }
    }

}