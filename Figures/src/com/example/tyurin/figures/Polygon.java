package com.example.tyurin.figures;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Class implements simple operations with polygons
 */
public class Polygon {
	
	/**
	 * Collection with size more than 2 and without self-intersections
	 * allows to use all class methods
	 * @param vertices of polygon
	 */
	public Polygon(AbstractCollection<Point> vertices, int number) {
		this.vertices.setSize(vertices.size());
		int idx = 0;
		for (Iterator<Point> it = vertices.iterator(); it.hasNext(); )
			this.vertices.set( idx++, new Point(it.next()) );
		this.number = number;
	}
	
	public Polygon(Polygon polygon) {
		this.vertices.setSize(polygon.size());
		int idx = 0;
		for (Iterator<Point> it = polygon.pointIterator(); it.hasNext(); )
			this.vertices.set( idx++, new Point(it.next()) );
		this.number = polygon.number;
	}
	
	/**
	 * @return perimeter of polygon
	 */
	public double perimeter() throws FewVerticesException {
	
		if ( vertices.size() < MIN_POINT_COUNT )
			throw new FewVerticesException();		
		
		double p = 0;
		for (Iterator<Line> it = lineIterator(); it.hasNext(); )
			p += it.next().distance();
		return p;
	}
	
	/**
	 * @return whether a polygon is convex or concave
	 */
	public boolean isConvex() throws SelfIntersectionException, FewVerticesException {
		
		if ( vertices.size() < MIN_POINT_COUNT )
			throw new FewVerticesException();
		if ( selfintersection() == true ) 
			throw new SelfIntersectionException();
		
		int signPlus  = 0,
			signMinus = 0;
		double z;
		
		int n = vertices.size();
		for (int i = 0; i < n; ++i) {
			int j = (i + 1) % n;
			int k = (i + 2) % n;
			z  = (vertices.get(j).x - vertices.get(i).x) * (vertices.get(k).y - vertices.get(j).y);
			z -= (vertices.get(j).y - vertices.get(i).y) * (vertices.get(k).x - vertices.get(j).x);
			if (z < 0) ++signMinus;
			if (z > 0) ++signPlus;
			if (signMinus != 0 && signPlus != 0)
				return false;
		}
		if (signMinus != 0 && signPlus != 0) 
			return false;
		else
			return true;
	}
	
	/**
	 * @return size of Polygon
	 */
	public int size() {
		return vertices.size();
	}
	
	/**
	 * @return iterator for all points in Polygon
	 */
	public Iterator<Point> pointIterator() {
        Iterator<Point> it = new Iterator<Point>() {

            private int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < vertices.size();
            }

            @Override
            public Point next() {
                return vertices.get(idx++);
            }

            @Override
            public void remove() {
                // remove is not supported
            }
        };
        return it;
    }
		
	/**
	 * @return iterator for all neighboring lines in Polygon
	 */
	public Iterator<Line> lineIterator() {
        Iterator<Line> it = new Iterator<Line>() {

            private int idx = 0;
            
            @Override
            public boolean hasNext() {
                return idx < vertices.size();
            }

            @Override
            public Line next() {
            	int prev = (idx == 0) ? vertices.size() - 1 : idx - 1;
            	return new Line(vertices.get(prev), vertices.get(idx++));
            }

            @Override
            public void remove() {
                // remove is not supported 
            }
        };
        return it;
    }

	protected boolean selfintersection() {
		
		// temp stub
		/*
		int size = vertices.size();
		Point prev, cur = vertices.get(size - 1);
		for (int i = 0; i < size; ++i)
		{
			last = cur;
			
			Line line1 = new Line(cur, last);
			
		}
		*/
		return false;
	}
	
	protected Vector<Point> vertices;
	protected int number;
	
	protected final int MIN_POINT_COUNT = 3;
	
}
