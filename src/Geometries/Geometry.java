package Geometries;

import Primitives.Point3D;
import Primitives.Vector;

/**
 * interface Geometry is the basic interface for all geometric objects
 * who are implementing getNormal method.
 *
 * @author Sara Teigman and Esther Burack
 */
public interface Geometry extends Intersectable
{
    /**
     *
     * @param p point on the geometry
     * @return normal
     */
    Vector getNormal(Point3D p);
}