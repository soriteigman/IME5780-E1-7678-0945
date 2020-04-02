package Geometries;

import Primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Ray r = new Ray(new Point3D(1,0,0), new Vector(0,1,0));
        Cylinder c = new Cylinder(1,r, 5);
        assertEquals(new Vector(0, -1, 0), c.getNormal(new Point3D(1.5, 0, 0)),"Cylinder.getNormal() result is wrong");
        assertEquals( new Vector(0, 1, 0), c.getNormal(new Point3D(1.5, 5, 0)),"Cylinder.getNormal() result is wrong");


        // ============ Boundary Tests ==============
        assertEquals( new Vector(0, -1, 0), c.getNormal(new Point3D(2, 0, 0)),"Cylinder.getNormal() result is wrong");
        assertEquals( new Vector(0, 1, 0), c.getNormal(new Point3D(2, 5, 0)),"Cylinder.getNormal() result is wrong");
    }
}
