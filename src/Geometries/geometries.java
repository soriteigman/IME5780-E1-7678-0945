package Geometries;

import Primitives.Point3D;
import Primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class geometries implements Intersectable {
    private List<Intersectable> _geometries = new LinkedList<Intersectable>();

    public geometries(Intersectable... _geometries) {
        add( _geometries);
    }

    public void add(Intersectable... geometries) {
        _geometries.addAll(Arrays.asList(geometries));

    }

    /**
     * @param ray
     * @return list of Point3D that intersect the collection
     */

    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;

        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray, maxDistance);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }

    public List<Intersectable> get_geometries() {
        return _geometries;
    }

    public void remove(Intersectable... intersectables) {
        for (Intersectable geo : _geometries) {
            _geometries.remove(geo);
        }
    }
}
