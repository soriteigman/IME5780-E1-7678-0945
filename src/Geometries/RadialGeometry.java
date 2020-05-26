package Geometries;

import Primitives.Color;
import elements.Material;

import static Primitives.Util.isZero;

/**
 * @author Sara Teigman and Esther Burack
 * RadialGeometry is an abstract class that defines
 * all radial geometries.
 */
public abstract class RadialGeometry extends Geometry
{
    double  _radius;

    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

    public RadialGeometry(RadialGeometry other){
        this._radius= other._radius;
    }

    public RadialGeometry(Color emission, Material material, double _radius) {
        super(emission, material);
        setRadius(_radius);
    }

    public double get_radius() {
        return _radius;
    }
    public void setRadius(double radius) {
        if (isZero(radius) || (radius < 0.0))
            throw new IllegalArgumentException("radius " + radius + " is not valid");
        this._radius = radius;
    }
}