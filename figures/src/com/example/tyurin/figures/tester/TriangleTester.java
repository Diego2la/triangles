package com.example.tyurin.figures.tester;

import java.util.Vector;

import com.example.tyurin.figures.Point;
import com.example.tyurin.figures.Polygon;
import com.example.tyurin.figures.Triangle;
import com.example.tyurin.figures.exception.NullArgumentException;
import com.example.tyurin.figures.exception.OpenFileException;
import com.example.tyurin.figures.exception.PolygonException;
import com.example.tyurin.figures.exception.ReadFileException;
import com.example.tyurin.figures.exception.TriangleNotFoundException;
import com.example.tyurin.figures.exception.VerticesCountException;

public class TriangleTester extends PolygonTester {

	@Override
	public void runTests() {		
		super.runTests();
		addTest( new TestInfo(testConstructorWithFourPoints()    , "testConstructorWithFourPoints")    );
		addTest( new TestInfo(testConstructorWithNullFileName()  , "testConstructorWithNullFileName")  );
		addTest( new TestInfo(testConstructorCannotFindFile()    , "testConstructorCannotFindFile")    );
		addTest( new TestInfo(testConstructorProtectedFile()     , "testConstructorProtectedFile")     );
		addTest( new TestInfo(testConstructorWrongFormat()       , "testConstructorWrongFormat")       );
		addTest( new TestInfo(testConstructorTriangleNotFound()  , "testConstructorTriangleNotFound")  );
		addTest( new TestInfo(testConstructorOkCreateFromFile()  , "testConstructorOkCreateFromFile")  );
		addTest( new TestInfo(testSquareThreePointInLine()       , "testSquareThreePointInLine")       );
		addTest( new TestInfo(testSquareThreePointNotInLine()    , "testSquareThreePointNotInLine")    );
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
		
	protected TestResult testConstructorCannotFindFile() {
		
		try {
			String fileName = new String(filePath + "ololo.bin"); // file not exists 	
			Triangle p = new Triangle(fileName, 1);
			p.perimeter();
		}catch (OpenFileException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult testConstructorProtectedFile() {
		
		try {
			String fileName = new String(filePath + "protected.bin");
			Triangle p = new Triangle(fileName, 1);
			p.perimeter();
		}catch (ReadFileException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult testConstructorWrongFormat() {
		
		try {
			String fileName = new String(filePath + "wrongFormat.bin");
			Triangle p = new Triangle(fileName, 1);
			p.perimeter();
		}catch (ReadFileException e) {
			return new TestOk();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();	
	}
	
	protected TestResult testConstructorTriangleNotFound() {
		
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
	
	protected TestResult testConstructorOkCreateFromFile() {
		
		try {
			String fileName = new String(filePath + "twoSuitable.bin");
			Triangle p = new Triangle(fileName, 2);
			double sq = p.square();
			if (Math.abs(sq - 2) < 0.0000001)
				return new TestOk();
			else
				return new TestFail();
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
	}
	
	protected TestResult testSquareThreePointInLine() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 2));
		v.set(2, new Point(0, 4));
		try {
			Triangle p = new Triangle(v);
			if (p.square() < 0.0000001) 
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}
	
	protected TestResult testSquareThreePointNotInLine() {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(3);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 2));
		v.set(2, new Point(4, 0));
		try {
			Triangle p = new Triangle(v);
			if ( Math.abs(p.square() - 4) < 0.0000001) 
				return new TestOk();
			
		} catch (PolygonException e) {
			return new TestFail("catching PolygonException(\"" + e.toString() + "\")");
		}
		return new TestFail();		
	}

	private final String filePath = new String("C:/Users/HP/Dropbox/Private/Projects/triangles/figures/tests/");
}
