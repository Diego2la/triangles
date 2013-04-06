package com.example.tyurin.figurestest;

import com.example.tyurin.figures.Point;

public class PointTester extends Tester {

	@Override
	public void fillTests() {		

		addTest( new TestInfo(testConstructor(), "testConstructor") );
	
	}
	
	@Override
	public String toString() {
		return "PointTester";
	}
		
	private TestResult testConstructor() {
		Point p = new Point();
		if (p.x == 0 && p.y == 0)
			return new TestOk();
		return new TestFail("points are not zero");		
	}

}
