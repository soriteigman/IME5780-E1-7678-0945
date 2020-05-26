package elements;
import Primitives.*;
import Geometries.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CameraIntegrationTest {
    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

    /**
     * tests sphere and camera ray intersection
     */
    @Test
    public void constructRayThroughPixelWithSphere1() {
        // TC01: view plain before sphere (r=1, 2 points)
        //constructing rays through all pixels
        Sphere sph =  new Sphere(1, new Point3D(0, 0, 3));
//        Ray ray = cam1.constructRayThroughPixel(3,3,0,0,1,3,3);
//        List<Point3D> results =  sph.findIntersections(ray);
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(2,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void constructRayThroughPixelWithSphere2(){
        Sphere sph =  new Sphere(2.5, new Point3D(0, 0, 2.5));
        // TC02: view plain in sphere (r=2.5, 18 points)
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(18,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test

    public void constructRayThroughPixelWithSphere3() {
        Sphere sph= new Sphere(2,new Point3D(0,0,2));
        // TC03: view plain before sphere (r=2, 10 points)
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(10,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test

    public void constructRayThroughPixelWithSphere4() {
        Sphere sph= new Sphere(4,new Point3D(0,0,1));
        // TC04: view plain before sphere (r=4, 10 points) have to fix it
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void constructRayThroughPixelWithSphere5()
    {
        Sphere sph= new Sphere(0.5,new Point3D(0,0,-1));
        // TC05: sphere before camera (r=0.5, 0 points)
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(0,count,"too bad");
        System.out.println("count: "+count);
    }

    /**
     * tests plane and camera ray intersection
     */
    @Test
    public void constructRayThroughPixelWithPlane1()
    {
        // TC01: plane parallels to camera (9 points)
        Plane pl= new Plane(new Point3D(0,-10,0), new Point3D(0,0,3), new Point3D(-10,0,0));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void constructRayThroughPixelWithPlane2() {
        // TC02: small angle with view plane (9 points)
        Plane pl= new Plane(new Point3D(3,-3,0), new Point3D(-2,-3,0), new Point3D(0,-1,1.5));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;

        for (int i = 0; i < 3; ++i) {

            for (int j = 0; j < 3; ++j) {

                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));

                if (results != null)

                    count += results.size();

            }

        }



        assertEquals(9,count,"too bad");

        System.out.println("count: "+count);



    }

    @Test
    public void constructRayThroughPixelWithPlane3() {
        //TC03: plane has 6 intersection points view plane rays.
        Plane pl= new Plane(new Point3D(0,5,5),new Vector(0,1,1));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(6,count,"too bad");
        System.out.println("count: "+count);
    }

    /**
     * tests triangle and camera ray intersection
     */
    @Test
    public void constructRayThroughPixelWithTriangle1() {
        //TCO1: triangle is contained in view plane and parallels to it (1 intersection)
        Triangle pl= new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2), new Point3D(-1,1,2));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(1,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void constructRayThroughPixelWithTriangle2() {
        //TCO2: bigger triangle parallels to plane (2 intersection points)
        Triangle pl= new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2), new Point3D(-1,1,2));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(2,count,"too bad");
        System.out.println("count: "+count);
    }

}
