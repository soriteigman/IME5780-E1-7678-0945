package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link Plane#Plane(Point3D, Point3D, Point3D)}
     */
    @Test
    public void testConstructor() {
        //tests plane's normal calculation
        Plane p1 = new Plane(new Point3D(-1,1,2), new Point3D(-4,2,2), new Point3D(-2,1,5));
        assertEquals((new Vector(3,9,1)).normalize(), p1.getNormal(), "Normal is not accurately calculated");
    }

    /**
     * Test method for {@link Plane#getNormal()}
     */
    @Test
    public void testNormal()
    {
        //tests plane's normal calculation
        Plane p1 = new Plane(new Point3D(-1,1,2), new Point3D(-4,2,2), new Point3D(-2,1,5));
        assertEquals( (new Vector(3,9,1)).normalize(), p1.getNormal(), "Normal is not accurately calculated");

    }

    // ============ Test FindIntersection ==============
    /**
     * Test method for {@link Plane#findIntersections(Ray)}
     */
    @Test
    public void testFindIntersections()
    {
        //create plane: Z=2
        Plane p1 = new Plane(new Point3D(0,0,2), new Point3D(2,3,2), new Point3D(4,1,2));
        // ============ Equivalence Partitions Tests ==============
        //TCO1: ray intersects with plane
        //checks amoutn of points returned
        LinkedList<Point3D> intersect= new LinkedList( p1.findIntersections(new Ray( new Point3D(0,2,1),new Vector(0,0,2))));
        if (intersect == null || intersect.size()!=1)
            fail("invalid amount of points returned");
        //checks that points are correct
        assertEquals(new Point3D(0,2,2), intersect.get(0) , "Error! Function does not find ray intersection");

        //TCO2: ray doesn't intersect with plane
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(0,0,10),new Vector(0,0,1))), "Error! Function finds intersection when there is none");

        // ============ Boundary Value Tests ==============
        //TCO3: ray is parallel to the plane and in plane
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(1,1,2),new Vector(1,2,0))), "invalid point for ray parallel to plane");

        //TCO4: ray is parallel to the plane and out of the plane
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(1,1,-2),new Vector(1,5,0))), "invalid point for ray parallel to plane");

        //TCO5: ray is orthogonal to the plane and starts in the plane
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(1,1,2),new Vector(0,1,0))), "invalid point for ray orthogonal to plane");

        //TCO6: ray is orthogonal to the plane and starts above it and hits it
        assertEquals(new Point3D(1,1,2), p1.findIntersections(new Ray( new Point3D(1,1,4),new Vector(0,0,-3))).get(0), "invalid point for ray orthogonal to plane");

        //TCO7: ray is orthogonal to the plane and starts above it and doesn't hit it
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(1,1,4),new Vector(0,0,2))), "invalid point for ray orthogonal to plane");

        //TCO8: ray is orthogonal to the plane and starts below the plane
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(1,1,1),new Vector(0,0,-3))), "invalid point for ray orthogonal to plane");

        //TCO9: ray starts in the plane and is not orthogonal or parallel
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(1,1,2),new Vector(1,2,3))), "invalid point for ray starting in plane");

        //TC10: ray begins in plane's point of reference
        assertEquals(null, p1.findIntersections(new Ray( new Point3D(0,0,2),new Vector(1,2,3))), "invalid point for ray starting in plane's point of reference");


    }
}