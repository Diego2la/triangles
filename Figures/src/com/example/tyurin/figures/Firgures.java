package com.example.tyurin.figures;

import java.util.Vector;

import com.example.tyurin.figurestest.PointTester;

public class Firgures {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Vector<Point> v = new Vector<Point>();
		v.setSize(4);
		v.set(0, new Point(0, 0));
		v.set(1, new Point(0, 3));
		v.set(2, new Point(1, 1));
		v.set(3, new Point(0, 3));
		try {
//			Triangle t = new Triangle(v);
//			System.out.println("Triangle perimetr = " + t.perimeter());
//			System.out.println("Triangle square = " + t.square());

//			Polygon p = new Polygon(v);
//			System.out.println("Polygon perimetr = " + p.perimeter());
//			System.out.println("Polygon isConvex = " + p.isConvex());
				
			String fileName = new String("C:/Users/HP/Dropbox/Private/Projects/Triangles/TriangleManager/test/test.bin");
			Triangle t = new Triangle(fileName, 2);
			System.out.println("Triangle perimetr = " + t.perimeter());
			
		
		} catch (PolygonException e) {
			e.printStackTrace();
		}
		
		PointTester pointTester = new PointTester();
		pointTester.test(true);
		
		
	}

}
