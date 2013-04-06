package com.example.tyurin.figures;


public class Line {
	
	public Point p1;
	public Point p2;
	
	Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * @param another line
	 * @return have lines intersection or not
	 */
	public boolean intersection(Line l) {
		double[] v = new double[4];
		v[0] = (l.p2.x - l.p1.x) * (p1.y - l.p1.y) - (l.p2.y - l.p1.y) * (p1.x - l.p1.x);
		v[1] = (l.p2.x - l.p1.x) * (p2.y - l.p1.y) - (l.p2.y - l.p1.y) * (p2.x - l.p1.x);
		v[2] = (p2.x - p1.x) * (l.p1.y - p1.y) - (p2.y - p1.y) * (l.p1.x - p1.x);
		v[3] = (p2.x - p1.x) * (l.p2.y - p1.y) - (p2.y - p1.y) * (l.p2.x - p1.x);
		return (v[0] * v[1] < 0) && (v[2] * v[3] < 0);
	}
	
	/**
	 * @return the distance between two line edges
	 */
	public double distance() {
		return Math.sqrt( (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y) );
	}
	
}