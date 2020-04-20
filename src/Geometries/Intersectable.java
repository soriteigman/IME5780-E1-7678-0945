package Geometries;

import Primitives.Point3D;
import Primitives.Ray;

import java.util.List;

public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);
}
