package renderer;

import elements.*;
import Geometries.*;
import Primitives.*;
import scene.Scene;

import java.util.List;

import static Primitives.Util.alignZero;

public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

    public Render(Scene _scene) {
        this._scene = _scene;
    }

    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    public Scene getScene() {
        return _scene;
    }


    public void renderImage() {

    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
        return null;
    }


    public void printGrid(int interval, java.awt.Color colorsep) {

    }


    private Color calcColor(Point3D gp) {
        return null;
    }



}