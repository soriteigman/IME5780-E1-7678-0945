package Geometries;

import Primitives .*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Tube t1 = new Tube( 3,new Ray(new Point3D(0,0,0), new Vector(1,2,3)));

        // ============ Equivalence Partitions Tests ==============
        assertEquals( new Vector(new Point3D(34, 68, 102)), t1.getNormal(new Point3D(2,4,8)),"");

    }
}