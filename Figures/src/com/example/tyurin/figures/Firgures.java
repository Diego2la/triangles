package com.example.tyurin.figures;

public class Firgures {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Point p1 = new Point(0, 6);
		p1.x = 0;
		
		Line l = new Line(p1, new Point(6, 10));
		System.out.println(l.distance());	

	}

}
