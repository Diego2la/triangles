package com.example.tyurin.figures;

import com.example.tyurin.figurestest.PointTester;
import com.example.tyurin.figurestest.PolygonTester;
import com.example.tyurin.figurestest.TriangleTester;

public class Firgures {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		test(true);
		
	}

	static boolean test(boolean showLog) {

		boolean res = true;
		
		if (res)
		{
			PointTester pointTester = new PointTester();
			res = pointTester.test(showLog);
			if (showLog) System.out.println();
		}
		
		if (res)
		{
			PolygonTester polygonTester = new PolygonTester();
			polygonTester.test(showLog);
			if (showLog) System.out.println();
		}
		
		if (res)
		{
			TriangleTester triangleTester = new TriangleTester();
			triangleTester.test(showLog);
			if (showLog) System.out.println();
		}
		
		if (showLog) System.out.println("All tests are complete with no errors");
		
		return res;
	}
}
