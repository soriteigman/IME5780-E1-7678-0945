package Geometries;

import Primitives.*;
import elements.Material;

/**
 * interface Geometry is the basic interface for all geometric objects
 * who are implementing getNormal method.
 *
 * @author Sara Teigman and Esther Burack
 */
public abstract class Geometry implements Intersectable
{
    protected Color emission;
    protected Material _material;

    public Geometry() {
        this(Color.BLACK);
    }

    public Geometry(Color emission, Material material) {
        this.emission = new Color(emission);
        this._material = new Material(material);
    }

    public Geometry(Color _emission) { this(_emission, new Material(0d, 0d, 0)); }

    /**
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * @return Material
     */
    public Material get_material() {
        return _material;
    }


    /**
     *
     * @param p point on the geometry
     * @return normal
     */
    public abstract Vector getNormal(Point3D p);

}