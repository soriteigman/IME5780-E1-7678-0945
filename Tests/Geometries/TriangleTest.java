package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test Method {@link Geometries.Triangle#getNormal(Point3D)}
     */
    @Test
    public void getNormalTest()
    {
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)), "Bad normal to triangle");
    }





    @Test
    public void findIntersections() {
        //Triangle t = new Triangle(new Point3D(2,0,0),new Point3D(0,0,2),new Point3D(0,2,0));
        Triangle t = new Triangle(new Point3D(0,2,0),new Point3D(0,0,2),new Point3D(2,0,0));
        //EP - Ray intersect triangle in the middle
        Ray r1 = new Ray(new Point3D(3.03,3.26,0), new Vector(-2.71,-2.59,1.01));


        List<Intersectable.GeoPoint> intersection = t.findIntersections(r1);

        assertEquals( 1, intersection.size(),"Error, no intersection with ray inside triangle");

        //EP - Ray no intersect triangle - outside edge
        Ray r2 = new Ray(new Point3D(-1,-2,1), new Vector(3,10,3));

        List<Intersectable.GeoPoint> no_intersections1 = t.findIntersections(r2);

        assertEquals( null, no_intersections1,"Error, intersection with ray outside triangle with edge");


        //EP - Ray no intersect triangle - outside vertex
        Ray r3 = new Ray(new Point3D(2.01,2.82,0), new Vector(0,0,2.15));

        List<Intersectable.GeoPoint> no_intersections2 = t.findIntersections(r3);

        assertEquals( null, no_intersections2,"Error, intersection with ray outside triangle with vertex");



        /***VBA***/

        //VBA - no intersection with ray on edge triangle
        Ray r4 = new Ray(new Point3D(2.57, 2.48, 0), new Vector(1.14,0,0.86));

        List<Intersectable.GeoPoint> no_intersections3 = t.findIntersections(r4);


        assertEquals( null, no_intersections3,"Error, intersection with ray on edge triangle");

        //VBA  - no intersection with ray on vertex triangle
        Ray r5 = new Ray(new Point3D(3.53,1.25,0), new Vector(2,0,0));

        List<Intersectable.GeoPoint> no_intersections4 = t.findIntersections(r5);

        assertEquals( null, no_intersections4,"Error, intersection with ray on vertex triangle");

        //VBA - no intersect, ray outside triangle, continue of edge..
        Ray r6 = new Ray(new Point3D(3.46,1.65,0), new Vector(0, -0.5, 2.5));


        List<Intersectable.GeoPoint> no_intersections5 = t.findIntersections(r6);

        assertEquals( null, no_intersections5,"Error, intersection with ray outside triangle continues edge");

    }



}