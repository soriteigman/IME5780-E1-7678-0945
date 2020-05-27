package Primitives;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class VectorTest {
    /**
     * Unit tests for primitives.Vector class
     * @author esther burack and sara teigman
     */

    @Test
    void subtractTest() {
        assertEquals( new Vector(1, 1, 1),
                new Vector(2, 3, 4).subtract(new Vector(1, 2, 3)),"Wrong vector subtract");
        try {
            new Vector(1, 2, 3).subtract(new Vector(1, 2, 3));
            fail("Subtract v from v must throw exception");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    void addTest() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.5);

        v1 = v1.add(v2);
        assertEquals(new Vector(0.0,0.0,-0.5),v1);

        v2 = v2.add(v1);
        assertEquals(new Vector(-1.0, -1.0, -2.0),v2);
    }

    @Test
    void scaleTest() {
        Vector v1 = new Vector(1,1,1);

        Vector v1test = v1.scale(1);
        assertEquals(new Vector(1,1,1),v1test);

        Vector v2test = v1test.scale(2);
        assertEquals(new Vector(2,2,2),v2test);

        Vector v3test = v2test.scale(-2);
        assertEquals(new Vector(-4,-4,-4),v3test);
    }


    @Test
    void dotProductTest() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals( -28d, v1.dotProduct(v2), 0.00001,"dotProduct() wrong value");
        Vector v3 = new Vector(0, 3, -2);
        assertEquals( 0d, v1.dotProduct(v3), 0.00001,"dotProduct() for orthogonal vectors is not zero");

    }

    @Test
    void crossProductTest() {
        Vector v1 = new Vector(3.5, -5.0, 10.0);
        Vector v2 = new Vector(2.5,7,0.5);
        Vector v3 = v1.crossProduct(v2);

        assertEquals( 0, v3.dotProduct(v2), 1e-10);
        assertEquals( 0, v3.dotProduct(v1), 1e-10);

        Vector v4 = v2.crossProduct(v1);

        System.out.println(v3.toString());
        System.out.println(v4.toString());

        try {
            v3.add(v4);
            fail("Vector (0,0,0) not valid");
        }
        catch  (IllegalArgumentException e)
        {
            assertTrue(e.getMessage()!= null);
        }
//        assertTrue(v3.length() >84);
        assertEquals(84,v3.length(),0.659);
    }

    @Test
    void lengthTest() {
        assertEquals( 5d, new Vector(0, 3, 4).length(), 0.00001,"length() wrong value");

    }

    @Test
    void testnormalize() {
        Vector v = new Vector(3.5, -5, 10);
        v.normalize();
        assertEquals(1, v.length(), 1e-10);

        try {
            Vector v1 = new Vector(0, 0, 0);
            v.normalize();
            fail("Didn't throw divide by zero exception!");
        } catch (IllegalArgumentException ex) {
            assertEquals("Point3D(0.0,0.0,0.0) not valid for vector head", ex.getMessage());
        }
        assertTrue(true);
    }
}