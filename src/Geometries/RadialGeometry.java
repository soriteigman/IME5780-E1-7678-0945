package Geometries;

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

    public double get_radius() {
        return _radius;
    }
}