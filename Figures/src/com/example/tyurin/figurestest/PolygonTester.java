package com.example.tyurin.figurestest;

import java.util.Vector;

import com.example.tyurin.figures.Polygon;
import com.example.tyurin.figures.Point;
import com.example.tyurin.figures.PolygonException;


public class PolygonTester extends Tester {

	@Override
	public void fillTests() {		

		addTest( new TestInfo(testConstructor(), "testConstructor") );
	
	}
		
	private TestResult testConstructor() {
		
		Vector<Point> v = new Vector<Point>(0);
		v.setSize(10);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 3));
		v.set(2, new Point(2, 3));
		v.set(2, new Point(2, 0));
		try {
			Polygon p = new Polygon(v);
			double d = p.perimeter();
			if (d != 10) return new TestFail("wrong perimatr value");
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestOk();		
	}

}
