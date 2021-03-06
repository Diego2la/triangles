package com.example.tyurin.figures;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Vector;

import com.example.tyurin.figures.exception.BadPointException;
import com.example.tyurin.figures.exception.EqualPointsException;
import com.example.tyurin.figures.exception.NullArgumentException;
import com.example.tyurin.figures.exception.PolygonException;
import com.example.tyurin.figures.exception.TypeOverflowException;
import com.example.tyurin.figures.exception.VerticesCountException;

/**
 * Class implements simple operations with polygons
 */
public class Polygon {
	
	/**
	 * @param vertices Collection of Points
	 * @throws VerticesCountException If collection size less than MIN_POINT_COUNT
	 * @throws NullPointException If input collection contains null elements
	 * @throws NullCollectionException If collection is null 
	 */
	public Polygon(AbstractCollection<Point> vertices) 
			throws NullArgumentException, VerticesCountException, 
			BadPointException, EqualPointsException {

		if (vertices == null)
			throw new NullArgumentException("vertices");
			
		int size = vertices.size();
		if ( size < MIN_POINT_COUNT )
			throw new VerticesCountException(size);		

		initVertices(vertices.iterator(), size);
		
		if (!noCopy())
			throw new EqualPointsException();
	}
	
	protected Polygon(Polygon polygon) {
		
		try {
			initVertices(polygon.pointIterator(), polygon.size());
		} catch (PolygonException e) {
			// cannot receive this exception
			// inside our class we have only valid data
			e.printStackTrace();
		}
	}
	
	/**
	 * @return perimeter of Polygon
	 */
	public double perimeter() throws TypeOverflowException {
		double p = 0;
		try {
			for (Iterator<Line> it = lineIterator(); it.hasNext(); ) {
				p += it.next().distance();
				if (Double.isInfinite(p) || Double.isNaN(p)) 
					throw new TypeOverflowException(); 
			}
		} catch(OverflowException e) {
			throw new TypeOverflowException(); 
		}
		return p;
	}
	
	/**
	 * @return whether a polygon is convex or concave
	 */
	public boolean isConvex() throws TypeOverflowException {
		
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
			if (Double.isInfinite(z) || Double.isNaN(z))
				throw new TypeOverflowException();
			if (signMinus != 0 && signPlus != 0)
				return false;
		}
		if (signMinus != 0 && signPlus != 0) 
			return false;
		else
			return true;
	}
	
	public AbstractCollection<Point> getPoints() {
		return new Vector(vertices);
	}
	
	/**
	 * @return size of Polygon
	 */
	public int size() {
		return vertices.size();
	}
	
	/**
	 * @return iterator for all Points in Polygon
	 */
	protected Iterator<Point> pointIterator() {
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
	 * @return iterator for copy of all neighboring Lines in Polygon
	 */
	protected Iterator<Line> lineIterator() {
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

	private void initVertices(Iterator<Point> it, int size) 
			throws NullArgumentException, BadPointException {
		this.vertices = new Vector<Point>();
		this.vertices.setSize(size);
		int idx = 0;
		while( it.hasNext() )
		{
			Point tempPoint = it.next();
			if (tempPoint == null)
				throw new NullArgumentException("Point");
			if (!tempPoint.isValid()) 
				throw new BadPointException();
			this.vertices.set( idx++, new Point(tempPoint) );
		}
	}
	
	private boolean noCopy() {
		Iterator<Point> it = pointIterator();
		while(it.hasNext()) {
			Point a = it.next();
			int count = 0;
			Iterator<Point> et = pointIterator();
			while(et.hasNext()) {
				Point b = et.next();
				if (a.x == b.x && a.y == b.y) ++count;				
			}
			if (count != 1) return false;
		}
		return true;
	}
	
	private Vector<Point> vertices;
	
	private final int MIN_POINT_COUNT = 3;
	
}
