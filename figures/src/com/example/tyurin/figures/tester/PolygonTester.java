package com.example.tyurin.figures.tester;

import java.util.Vector;

import com.example.tyurin.figures.Polygon;
import com.example.tyurin.figures.Point;
import com.example.tyurin.figures.exception.BadPointException;
import com.example.tyurin.figures.exception.EqualPointsException;
import com.example.tyurin.figures.exception.NullArgumentException;
import com.example.tyurin.figures.exception.PolygonException;
import com.example.tyurin.figures.exception.TypeOverflowException;
import com.example.tyurin.figures.exception.VerticesCountException;


public class PolygonTester extends Tester {

	@Override
	public void runTests() {		
		addTest( new TestInfo(test1(), "test1") );
		addTest( new TestInfo(test2(), "test2") );
		addTest( new TestInfo(test3(), "test3") );
		addTest( new TestInfo(test4(), "test4") );
		addTest( new TestInfo(test5(), "test5") );
		addTest( new TestInfo(test6(), "test6") );
		addTest( new TestInfo(test7(), "test7") );
		addTest( new TestInfo(test8(), "test8") );
		addTest( new TestInfo(test9(), "test9") );
		addTest( new TestInfo(test10(), "test10") );
		addTest( new TestInfo(test11(), "test11") );
		addTest( new TestInfo(test12(), "test12") );
	}
		
	@Override
	public String toString() {
		return "tester";
	}
	
	protected TestResult test1() {
		
		Vector<Point> v = null;
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		} catch (NullArgumentException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}	
		return new TestFail();
	}
	
	protected TestResult test2() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(0, 1));
		v.set(1, new Point(0, 2));
		v.set(2, new Point(0, 3));
		v.set(3, null);
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		}catch (NullArgumentException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test3() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(2);
		v.set(0, new Point(3, 4));
		v.set(1, new Point(3, 5));
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
	
	protected TestResult test4() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(1, 1));
		v.set(2, new Point(Double.POSITIVE_INFINITY, 2));
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		}catch (BadPointException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test5() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(1, 1));
		v.set(2, new Point(2, Double.NaN));
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		}catch (BadPointException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test6() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(1, 1));
		v.set(2, new Point(1, 1));
		try {
			Polygon p = new Polygon(v);
			p.perimeter();
		}catch (EqualPointsException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test7() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 4));
		v.set(2, new Point(3, 4));
		v.set(3, new Point(3, 0));
		try {
			Polygon p = new Polygon(v);
			double perim = p.perimeter();
			if (Math.abs(perim - 14) < CALC_DEVIATION) 
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test8() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(1, 7));
		v.set(1, new Point(1, 3));
		v.set(2, new Point(10, 3));
		v.set(3, new Point(10, 7));
		try {
			Polygon p = new Polygon(v);
			if (Math.abs(p.perimeter() - 26) < CALC_DEVIATION) 
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test9() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(Double.MAX_VALUE - 1, 7));
		v.set(1, new Point(Double.MIN_VALUE + 1, -100));
		v.set(2, new Point(1, 7));
		try {
			Polygon p = new Polygon(v);
			double per = p.perimeter();			
			if (per != 123) per += 1; // to exclude warning
		} catch (TypeOverflowException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test10() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(0, 1));
		v.set(1, new Point(1, 0));
		v.set(2, new Point(0, -2));
		v.set(3, new Point(-3, 0));
		try {
			Polygon p = new Polygon(v);
			if (p.isConvex())
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test11() {
		
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

	protected TestResult test12() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(Double.MAX_VALUE - 1, 0));
		v.set(2, new Point(0, Double.MAX_VALUE - 1));
		try {
			Polygon p = new Polygon(v);
			p.isConvex();
		} catch (TypeOverflowException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected final double CALC_DEVIATION = 0.000001;
}
