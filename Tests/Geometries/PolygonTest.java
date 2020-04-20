package Geometries;

import Primitives.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Polygons
 * @author Dan
 *
 */
class PolygonTest {

    /**
     * Test method for
     * {@link Geometries.Polygon#(Primitives.Point3D, Primitives.Point3D, Primitives.Point3D, Primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {
        }

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {
        }

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {
        }

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

    }

    /**
     * Test method for {@link Geometries.Polygon#getNormal(Primitives.Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)), "Bad normal to trinagle");

    }

    @Test
    public void testFindIntersections() {
        Polygon p = new Polygon(new Point3D(4.0, 4.0, 0.0), new Point3D(4.0, 4.0, 4.0), new Point3D(-4.0, 4.0, 4.0), new Point3D(-4.0, 4.0, 0.0));

        // ============ Equivalence Partitions Tests ==============

        //case 1- ray intersects with polygon
        Ray r = new Ray(new Point3D(1.0, -5.0, 3.0), new Vector(0.0, 3.0, 0.0));
        List<Point3D> l = p.findIntersections(r);
        List<Point3D> expectList = new ArrayList<Point3D>();
        expectList.add(new Point3D(1.0, 4.0, 3.0));
        assertEquals(expectList, l);

        //case 2- ray intersects with plane but outside the polygon against edge
        r = new Ray(new Point3D(6.0, -1.0, 0.0), new Vector(0.0, 3.0, 0.0));
        l = p.findIntersections(r);
        assertEquals(null, l);

        //case 3- ray intersects with plane but outside the polygon against vertex
        r = new Ray(new Point3D(5.0, 4.0, 4.0), new Vector(0.0, 3.0, 0.0));
        l = p.findIntersections(r);


    }
}