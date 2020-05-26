package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class geometriesTest {

    /**
     * Test method for {@link geometries#findIntersections(Ray)}
     */
    @Test
    void findIntersections() {
        // ============ Boundary Value Analysis ==============
        // TC01: empty list
        geometries g1 = new geometries();
        assertEquals(null, g1.findIntersections(new Ray( new Point3D(4,5,4),new Vector(1,2,3))), "Error! Geometries found intersections when there were no shapes");

        // TC02: no shape hits ray
        geometries g2 = new geometries(new Sphere(1, new Point3D(4,4,4)), new Plane(new Point3D(0,6,0), new Point3D(0,0,10), new Point3D(3,0,0)), new Polygon(new Point3D(3,5,0), new Point3D(9,11,0), new Point3D(2,1,0),  new Point3D(1,0,0)), new Triangle(new Point3D(1,3,5), new Point3D(1,1,1), new Point3D(2,-5,3)));
        assertEquals(null, g2.findIntersections(new Ray( new Point3D(-4,-3,-2),new Vector(0,0,-1))), "Error! Geometries found points when there were none");

        //TCO3: one shape hits ray
        g2.add(new Sphere(15, new Point3D(-4,-3, -15)));
        try {
            assertEquals(1, g2.findIntersections(new Ray( new Point3D(-4, -3, -2),new Vector(0, 0, -1))).size(), "Error! Geometries found invalid amount of intersections");
        }
        catch(NullPointerException e)//intersection list was null
        {
            fail ("Error! Geometries didn't find the one intersection point");
        }

        //TCO4: all shapes hit ray
        geometries g4 = new geometries(new Sphere(1, new Point3D(0,0,4)), new Plane( new Point3D(0,0,6),new Vector(4,5,0)),
                new Triangle(new Point3D(-3,-2,8), new Point3D(-1,9,8), new Point3D(2,-2,8)),
                new Polygon(new Point3D(-3,-2,10), new Point3D(-1,9,10), new Point3D(2,3,10), new Point3D(2,-2,10)));
        try {
            assertEquals(g4.get_geometries().size(), g4.findIntersections(new Ray( new Point3D(0,0, -2),new Vector(0, 0, 1))).size(), "Error! Geometries didn't find all intersections");
        }
        catch(NullPointerException e)//intersection list was null
        {
            fail ("Error! Geometries found no intersections when all intersect");
        }

        //TCO5: ray hits all shapes but one
        g4.add(new Sphere(1, new Point3D(-4,-3, -15)));//adds shape that ray doesn't hit
        assertEquals(g4.get_geometries().size()-1, g4.findIntersections(new Ray( new Point3D(0,0, -2),new Vector(0, 0, 1))).size(), "Error! Geometries found invalid amount of intersections");

        // ============ Equivalence Partitions Tests ==============
        //TCO6: some shapes hit ray and some don't
        g4.add(new Sphere(1, new Point3D(15,6,50)));
        try {
            assertEquals(g4.get_geometries().size()-2, g4.findIntersections(new Ray( new Point3D(0,0, -2),new Vector(0, 0, 1))).size(), "Error! Geometries found invalid amount of intersections");
        }
        catch(NullPointerException e)//intersection list was null
        {
            fail ("Error! Geometries found invalid amount of intersections");
        }



    }
}