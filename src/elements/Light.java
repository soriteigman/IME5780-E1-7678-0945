package elements;

import Primitives.*;

public abstract class Light {
    /**
     * _intensity value, set to protected
     */
    protected Color _intensity;

    public Color getIntensity() {
        return new Color(_intensity);
    }
}