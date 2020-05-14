package Geometries;

import Primitives.*;

/**
 * interface Geometry is the basic interface for all geometric objects
 * who are implementing getNormal method.
 *
 * @author Sara Teigman and Esther Burack
 */
public abstract class Geometry implements Intersectable
{
    public Geometry(Color emission) {
        this.emission = emission;
    }

    public Geometry() {
        this(Color.BLACK);
    }

    /**
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    protected Color emission;
    /**
     *
     * @param p point on the geometry
     * @return normal
     */
    public abstract Vector getNormal(Point3D p);
}