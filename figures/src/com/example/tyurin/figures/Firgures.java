package com.example.tyurin.figures;

import com.example.tyurin.figures.tester.PolygonTester;
import com.example.tyurin.figures.tester.TriangleTester;

public class Firgures {

	public static void main(String[] args) {
		
		String resStr = test(false) ? "success" : "fail";
		System.out.println("Tests result : " + resStr);
		
	}

	static boolean test(boolean showLog) {

		boolean res = true;
		
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
		
		
		return res;
	}
}
