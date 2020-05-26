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
    public void findIntersectionsTest() {
        Plane plane = new Plane(new Point3D(1,1,0), new Point3D(0,0,1), new Point3D(1,0,1));

        // ============ Equivalence Partitions Tests ==============
        //Ray intersects the plane
        Ray ray1 = new Ray(new Point3D(5,5,5), new Vector(1,2,-3));
        assertEquals(plane.findIntersections(ray1).size(), 1,"Ray intersects the plane");

        //Ray does not intersect the plane
        Ray ray2 = new Ray(new Point3D(1,2,3), new Vector(1,0,2));
        assertNull(plane.findIntersections(ray2),"Ray does not intersects the plane");

        // =============== Boundary Values Tests ==================
        // Ray is parallel to the plane - the ray included in the plane
        Ray ray3 = new Ray(new Point3D(2,1,0), new Vector(0,0,1));
        assertNull(plane.findIntersections(ray3),"Ray is parallel to the plane - the ray included in the plane");

        // Ray is parallel to the plane - the ray not included in the plane
        Ray ray4 = new Ray(new Point3D(2,2,0), new Vector(2,1,1));
        assertNull(plane.findIntersections(ray4),"Ray is parallel to the plane - the ray not included in the plane");

        //Ray is orthogonal to the plane - before
        Ray ray5 = new Ray(new Point3D(0,0,0), new Vector(0,1,1));
        assertEquals(plane.findIntersections(ray5).size(), 1,"Ray is orthogonal to the plane - before");

        //Ray is orthogonal to the plane - in
        Ray ray6 = new Ray(new Point3D(0,0.5,0.5), new Vector(0,1,1));
        assertNull(plane.findIntersections(ray6),"Ray is orthogonal to the plane - in");

        //Ray is orthogonal to the plane - after
        Ray ray7 = new Ray(new Point3D(0,0,0), new Vector(0,-1,-1));
        assertNull(plane.findIntersections(ray7),"Ray is orthogonal to the plane - after");

        //Ray is neither orthogonal nor parallel to and begins at the plane
        Ray ray8 = new Ray(new Point3D(0,0.5,0.5), new Vector(0,2,1));
        assertNull(plane.findIntersections(ray8),"Ray is neither orthogonal nor parallel to and begins at the plane");

        //Ray is neither orthogonal nor parallel to and begins at the plane - start point is A
        Ray ray9 = new Ray(new Point3D(1,1,0), new Vector(3,2,5));
        assertNull(plane.findIntersections(ray9),"Ray is neither orthogonal nor parallel to and begins at the plane");
    }

}
