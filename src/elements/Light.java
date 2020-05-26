package elements;

import Primitives.Color;
import Primitives.Point3D;

/**
 * abstract class Light helps with defining other types of light classes
 */
public abstract class Light {
    /**
     * _intensity value, set to protected
     */
    protected Color _intensity;

    public Color getIntensity() {
        return new Color(_intensity);
    }
}
