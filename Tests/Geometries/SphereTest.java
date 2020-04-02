package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     * Test method for {@link Geometries.Sphere#getNormal(Point3D)}
     */
    @Test
    void getNormal() {
        Sphere s = new Sphere(sqrt(57), Point3D.ZERO);//x^2 + y^2 + z^2 = 57
        double expCal = sqrt(228);//to save calculations for the point
        Vector expected = new Vector(8/expCal, 8/expCal, 10/expCal);//expected vector
        assertEquals(expected, s.getNormal(new Point3D(4,4,5)), "normal to point on sphere in inaccurate");
        //the normal to the point (4,4,5) on the sphere x^2 + y^2 + z^2 = 57 is the expected vector in the calculations

    }
}