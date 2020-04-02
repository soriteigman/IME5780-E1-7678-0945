package Geometries;

import Primitives .*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TubeTest {

    @Test
    void getNormal() {
        Vector expected=new Vector(0,1,0);
        //o=(0,0,5), t=5, po=(0,0,3)  po.normalize=(0,0,1)
        Tube t1 = new Tube( 5,new Ray(new Point3D(0,0,0),new Vector(0,0,1)));

        // ============ Equivalence Partitions Tests ==============
        assertEquals(expected , t1.getNormal(new Point3D(0,3,5)),"normal to point on tube in inaccurate");

    }
}