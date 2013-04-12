package com.example.tyurin.figures;

import com.example.tyurin.figures.tester.PolygonTester;
import com.example.tyurin.figures.tester.TriangleTester;

public class Firgures {

	public static void main(String[] args) {
		
		boolean testRes = test(false);
		String str = new String(testRes ? "success" : "fail");
		if (!testRes) test(true);
		System.out.println("Tests result : " + str);
		
	}

	static boolean test(boolean showLog) {

		boolean res = true;
		
		if (res)
		{
			PolygonTester polygonTester = new PolygonTester();
			res = polygonTester.test(showLog);
			if (showLog) System.out.println();
		}
		
		if (res)
		{
			TriangleTester triangleTester = new TriangleTester();
			res = triangleTester.test(showLog);
			if (showLog) System.out.println();
		}
		
		return res;
	}
}
