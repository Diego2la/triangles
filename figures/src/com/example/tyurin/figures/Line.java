package com.example.tyurin.figures;

class OverflowException extends Exception{
	private static final long serialVersionUID = 1L;
}

class Line {
	
	public Point p1;
	public Point p2;
	
	Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * @param another line
	 * @return have lines intersection or not
	 * @throws OverflowException if overflow while calculating
	 */
	public boolean intersection(Line l) throws OverflowException {
		double[] v = new double[4];
		v[0] = (l.p2.x - l.p1.x) * (p1.y - l.p1.y) - (l.p2.y - l.p1.y) * (p1.x - l.p1.x);
		v[1] = (l.p2.x - l.p1.x) * (p2.y - l.p1.y) - (l.p2.y - l.p1.y) * (p2.x - l.p1.x);
		v[2] = (p2.x - p1.x) * (l.p1.y - p1.y) - (p2.y - p1.y) * (l.p1.x - p1.x);
		v[3] = (p2.x - p1.x) * (l.p2.y - p1.y) - (p2.y - p1.y) * (l.p2.x - p1.x);
		
		if (Double.isInfinite(v[0] * v[1]) || Double.isNaN(v[0] * v[1]) ||
			Double.isInfinite(v[2] * v[3]) || Double.isNaN(v[2] * v[3]) )
			throw new OverflowException();
		return (v[0] * v[1] < 0) && (v[2] * v[3] < 0);
	}
	
	/**
	 * @return the distance between two line edges
	 * @throws OverflowException if overflow while calculating 
	 */
	public double distance() throws OverflowException {
		double d = (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
		if (Double.isInfinite(d) || Double.isNaN(d))
			throw new OverflowException();
		return Math.sqrt(d);
	}
	
}