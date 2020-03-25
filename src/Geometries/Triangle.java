package Geometries;

import Primitives.Point3D;

public class Triangle extends Polygon
{

    //@Override
    //public boolean equals(Object obj) {
    //  if (this == obj) return true;
    //if (obj == null) return false;
    //if (!(obj instanceof Triangle)) return false;

    //Triangle tr = (Triangle) obj;

    //return _vertices.get(0).equals(tr._vertices.get(0)) &&
    //vertices.get(1).equals(tr._vertices.get(1)) &&
    //_vertices.get(2).equals(tr._vertices.get(2));
    //}

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(new Point3D[]{p1, p2, p3});
    }

    @Override
    public String toString()
    {
        String result = "";
        for (Point3D p : _vertices )
        {
            result += p.toString();
        }
        return  result;
    }

}