package com.example.tyurin.figures.tester;

import java.util.Vector;

import com.example.tyurin.figures.Point;
import com.example.tyurin.figures.Triangle;
import com.example.tyurin.figures.exception.NullArgumentException;
import com.example.tyurin.figures.exception.PolygonException;
import com.example.tyurin.figures.exception.VerticesCountException;

public class TriangleTester extends PolygonTester {

	@Override
	public void runTests() {		

		super.runTests();
		addTest( new TestInfo(testConstructorWithFourPoints()  , "testConstructorWithFourPoints")  );
		
	}
		
	@Override
	public String toString() {
		return "TriangleTester";
	}
	
	protected TestResult testConstructorWithFourPoints() {
			
		Vector<Point> v = new Vector<Point>();
		v.setSize(2);
		v.set(0, new Point(3, 4));
		v.set(1, new Point(3, 4));
		try {
			Triangle p = new Triangle(v);
			p.perimeter();
		}catch (VerticesCountException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult testConstructorWithNullFileName() {
		
		try {
			String fileName = null;
			Triangle p = new Triangle(fileName, 1);
			p.perimeter();
		}catch (NullArgumentException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
		
}
