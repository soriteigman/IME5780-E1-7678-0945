package renderer;

import elements.*;
import Geometries.*;
import Primitives.*;
import scene.Scene;
import Geometries.Intersectable.GeoPoint;
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
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    //java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    //_imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        GeoPoint result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();

        for (GeoPoint geo : intersectionPoints) {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist) {
                mindist = distance;
                result = geo;
            }
        }
        return result;
    }


    /**
     * Printing the grid with a fixed interval between lines
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNy();
        double collumns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int collumn = 0; collumn < collumns; ++collumn) {
                if (collumn % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(collumn, row, colorsep);
                }
            }
        }
    }


    /**
     * Calculate the color intensity in a point
     *
     * @param gp intersection the point for which the color is required
     * @return the color intensity
     */
    private Color calcColor(GeoPoint gp) {
        Color result = _scene.getAmbientLight().getIntensity();
        result = result.add(gp.getGeometry().getEmission());
        List<LightSource> lights = _scene.get_lights();

        Vector v = gp.getPoint().subtract(_scene.getCamera().get_p0()).normalize();
        Vector n = gp.getGeometry().getNormal(gp.getPoint());

        Material material = gp.getGeometry().get_material();
        int nShininess = material.getnShininess();
        double kd = material.getkD();
        double ks = material.getkS();
        if (_scene.get_lights() != null) {
            for (LightSource lightSource : lights) {

                Vector l = lightSource.getL(gp.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));

                /*if (sign(nl) == sign(nv)) {
                    Color ip = lightSource.getIntensity(gp.getPoint());
                    result = result.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n, nl, v, nShininess, ip)
                    );
                }*/
            }
        }

        return result;
    }
}