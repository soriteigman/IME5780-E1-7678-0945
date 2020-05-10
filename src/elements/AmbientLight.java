package elements;

import Primitives.Color;

public class AmbientLight {

    private Color _intensity;

    public void setIntensity(Color _intensity) {
        this._intensity = _intensity;
   }

    public AmbientLight(Color _intensity, double ka) {
        this._intensity = _intensity.scale(ka);
    }

    public Color getIntensity() {
        return _intensity;
    }
}