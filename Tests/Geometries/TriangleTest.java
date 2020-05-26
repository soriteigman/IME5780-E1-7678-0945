package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

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
    public void testFindIntersections()
    {

        Polygon p1 = new Triangle(new Point3D(2,3,2),new Point3D(4,3,2), new Point3D(4,1,2));

        // ============ Equivalence Partitions Tests ==============

        //TCO1: ray intersects with triangle
        LinkedList<Intersectable.GeoPoint> intersects = new LinkedList<Intersectable.GeoPoint>( p1.findIntersections(new Ray( new Point3D(3.5,2,1),new Vector(0,0,1))));
        //checks amount of points returned
        if (intersects == null || intersects.size()!=1 )
            fail("invalid amount of points returned");
        //checks that points are correct
        assertEquals(new Point3D(3.5,2,2), intersects.get(0) , "Error! Function does not find ray intersection");

        //TCO2: ray doesn't intersect
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(3,0,2),new Vector(0,0,-1))), "Error! Function finds intersection when there is none");

        // ============ Boundary Value Tests ==============
        //TCO3: ray is parallel to the triangle, in plane
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(4,4,2),new Vector(1,0,0))), "invalid point for ray parallel to plane");

        //TCO4: ray is parallel to the triangle and out of the plane
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(1,1,-2),new Vector(1,5,0))), "invalid point for ray parallel to plane");

        //TCO5: ray is orthogonal to the plane, starts above it
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(6,2,5),new Vector(0,0,1))), "invalid point for ray orthogonal to plane");

        //TCO6: ray is orthogonal to the plane and starts below it
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(6,2,1),new Vector(0,0,1))), "invalid point for ray orthogonal to plane");

        //TC07: ray starts in the plane and is not orthogonal or parallel
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(-5,3,2),new Vector(1,2,3))), "invalid point for ray starting in plane");

        //TC08: ray begins in polygon's vertice
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(2,3,2),new Vector(1,2,7))), "invalid point for ray starting in polygon's vertice");

        //TC09: ray begins on triangle's edge
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(3,2,2),new Vector(1,2,7))), "invalid point for ray starting on polygon's edge");

        //TC10: ray intersects with triangle's edge
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(3,2,-2),new Vector(0,0,1))), "invalid point for ray intersecting with polygon's edge");


    }


}