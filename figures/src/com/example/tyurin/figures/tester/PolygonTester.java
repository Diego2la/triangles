package com.example.tyurin.figures.tester;

import java.util.Iterator;
import java.util.Vector;

import com.example.tyurin.figures.Polygon;
import com.example.tyurin.figures.Point;
import com.example.tyurin.figures.exception.NullCollectionException;
import com.example.tyurin.figures.exception.NullPointException;
import com.example.tyurin.figures.exception.PolygonException;
import com.example.tyurin.figures.exception.VerticesCountException;


public class PolygonTester extends Tester {

	@Override
	public void runTests() {		

		addTest( new TestInfo(testConstructorWithNullVector()  , "testConstructorWithNullVector")  );
		addTest( new TestInfo(testConstructorWithNullPoint()   , "testConstructorWithNullPoint")   );
		addTest( new TestInfo(testConstructorWithTwoPoints()   , "testConstructorWithTwoPoints")   );
		addTest( new TestInfo(testConstructorWithThreePoints() , "testConstructorWithThreePoints") );
		addTest( new TestInfo(testPerimetrWithFourPoints()     , "testPerimetrWithFourPoints")     );
		addTest( new TestInfo(testPerimetrWithTwoLines()       , "testPerimetrWithTwoLines")       );
		addTest( new TestInfo(testCovexWithThreePoints()       , "testCovexWithThreePoints")       );
		addTest( new TestInfo(testCovexWithConcaveFigure()     , "testCovexWithConcaveFigure")     );
		
	}
		
	@Override
	public String toString() {
		return "PolygonTester";
	}
	
	protected TestResult testConstructorWithNullVector() {
		
		Vector<Point> v = null;
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		} catch (NullCollectionException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}	
		return new TestFail();
	}
	
	protected TestResult testConstructorWithNullPoint() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 0));
		v.set(2, new Point(0, 0));
		v.set(3, null);
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		}catch (NullPointException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult testConstructorWithTwoPoints() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(2);
		v.set(0, new Point(3, 4));
		v.set(1, new Point(3, 4));
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		}catch (VerticesCountException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult testConstructorWithThreePoints() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(3, 4));
		v.set(1, new Point(3, 4));
		v.set(2, new Point(3, 4));
		try {
			Polygon p = new Polygon(v);
			int count = 0;
			Iterator<Point> it = p.pointIterator();
			while (it.hasNext()) {
				Point cur = it.next();
				if (cur.x != 3 || cur.y != 4)
					return new TestFail("Wrong point in Polygon");
				++count;
			}
			if (count != 3) return new TestFail("Cannot find 3 Points");
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestOk();		
	}

	protected TestResult testPerimetrWithFourPoints() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 4));
		v.set(2, new Point(3, 4));
		v.set(3, new Point(3, 0));
		try {
			Polygon p = new Polygon(v);
			if (p.perimeter() - 14 < 0.0000001) 
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult testPerimetrWithTwoLines() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(1, 7));
		v.set(1, new Point(1, 3));
		v.set(2, new Point(1, 7));
		v.set(3, new Point(10, 7));
		try {
			Polygon p = new Polygon(v);
			if (p.perimeter() - 26 < 0.0000001) 
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult testCovexWithThreePoints() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(1, 7));
		v.set(1, new Point(1, 3));
		v.set(2, new Point(4, 47.99));
		try {
			Polygon p = new Polygon(v);
			if (p.isConvex())
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult testCovexWithConcaveFigure() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(5);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(3, 0));
		v.set(2, new Point(3, 7));
		v.set(3, new Point(2, 6));
		v.set(4, new Point(0, 10));
		try {
			Polygon p = new Polygon(v);
			if (!p.isConvex())
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}

}
