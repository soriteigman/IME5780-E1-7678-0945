package elements;

import Primitives.*;


public class DirectionalLight extends Light implements LightSource {
    private final Vector _direction;

    /**
     * Initialize directional light with it's intensity and direction, direction
     * vector will be normalized.
     *
     * @param colorintensity intensity of the light
     * @param direction      direction vector
     */

    public DirectionalLight(Color colorintensity, Vector direction) {
        _intensity = colorintensity;
        _direction = direction.normalized();
    }

    /**
     * @param p the lighted point is not used and is mentioned
     *          only for compatibility with LightSource
     * @return fixed intensity of the directionLight
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
        //       return _intensity;
    }

    //instead of getDirection()
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
