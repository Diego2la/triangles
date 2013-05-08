package com.example.tyurin.figures.tester;

import java.util.Vector;

import com.example.tyurin.figures.Point;
import com.example.tyurin.figures.Triangle;
import com.example.tyurin.figures.exception.EqualPointsException;
import com.example.tyurin.figures.exception.FileFormatException;
import com.example.tyurin.figures.exception.NullArgumentException;
import com.example.tyurin.figures.exception.OpenFileException;
import com.example.tyurin.figures.exception.PolygonException;
import com.example.tyurin.figures.exception.TriangleNotFoundException;
import com.example.tyurin.figures.exception.TypeOverflowException;
import com.example.tyurin.figures.exception.VerticesCountException;

public class TriangleTester extends PolygonTester {

	@Override
	public void runTests() {		
		super.runTests();
		addTest( new TestInfo(test13(), "test13") );
		addTest( new TestInfo(test14(), "test14") );
		addTest( new TestInfo(test15(), "test15") );
		addTest( new TestInfo(test16(), "test16") );
		addTest( new TestInfo(test17(), "test17") );
		addTest( new TestInfo(test18(), "test18") );
		addTest( new TestInfo(test19(), "test19") );
		addTest( new TestInfo(test20(), "test20") );
		addTest( new TestInfo(test21(), "test21") );
		addTest( new TestInfo(test22(), "test22") );
	}
		
	@Override
	public String toString() {
		return "TriangleTester";
	}
	
	protected TestResult test13() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(1, 1));
		v.set(2, new Point(2, 2));
		v.set(3, new Point(3, 3));
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
	
	protected TestResult test14() {
		
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
		
	protected TestResult test15() {
		
		try {
			String fileName = new String(filePath + "a.bin"); // file not exists 	
			Triangle p = new Triangle(fileName, 1);
			p.perimeter();
		}catch (OpenFileException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult test16() {
		
		try {
			String fileName = new String(filePath + "protected.bin");
			Triangle p = new Triangle(fileName, 1);
			p.perimeter();
		}catch (OpenFileException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult test17() {
		
		try {
			String fileName = new String(filePath + "wrongFormat.bin");
			Triangle p = new Triangle(fileName, 1);
			p.perimeter();
		}catch (FileFormatException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult test18() {
		
		try {
			String fileName = new String(filePath + "noTriangleNumber7.bin");
			Triangle p = new Triangle(fileName, 7);
			p.perimeter();
		}catch (TriangleNotFoundException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult test19() {
		
		try {
			String fileName = new String(filePath + "equal.bin");
			Triangle p = new Triangle(fileName, 7);
			p.perimeter();
		}catch (EqualPointsException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult test20() {
		
		try {
			String fileName = new String(filePath + "twoSuitable.bin");
			Triangle p = new Triangle(fileName, 7);
			double sq = p.square();
			if (Math.abs(sq - 4) < CALC_DEVIATION)
				return new TestOk();
			else
				return new TestFail();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
					
	}
	
	protected TestResult test21() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 4));
		v.set(2, new Point(0, 3));
		try {
			Triangle p = new Triangle(v);
			if (Math.abs(p.square()) < CALC_DEVIATION)
				return new TestOk();			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult test22() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(Double.MAX_VALUE - 1, 0));
		v.set(2, new Point(0, Double.MAX_VALUE - 1));
		try {
			Triangle p = new Triangle(v);
			p.square();			
		} catch (TypeOverflowException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	private final String filePath = new String("C:/Users/HP/Dropbox/Private/Projects/triangles/figures/tests/");
}
