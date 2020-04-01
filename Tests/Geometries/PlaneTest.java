package Geometries;

import Primitives.Point3D;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link Plane#getNormal()}
     */
    @Test
    void getNormalTest() {
        //tests plane's normal calculation
        Plane p1 = new Plane(new Point3D(-1,1,2), new Point3D(-4,2,2), new Point3D(-2,1,5));
        assertEquals( (new Vector(3,9,1)).normalize(), p1.getNormal(), "Normal is not accurately calculated");
    }


}