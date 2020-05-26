package elements;

import Primitives.Color;

public class AmbientLight  extends  Light{

    public AmbientLight(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }
}
