package elements;

import Primitives.Color;

/**
 * class to represent the light around the object
 */
public class AmbientLight  extends  Light{
    /**
     * constructor for AmbientLight
     * @param ia
     * @param ka
     */
    public AmbientLight(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }
}
