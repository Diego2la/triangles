package com.example.tyurin.figures;

public class Point {
	public double x;
	public double y;
	public Point() {}
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point(Point p) {
		x = p.x;
		y = p.y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
