package com.example.tyurin.figures;

class PolygonException extends Exception
{
	PolygonException(String error)
	{
		super();
		this.error = error;
	}
	
	@Override
	public String toString() {
		return "PolygonException( " + error + " )"; 
 	}
	
	protected String error;
	private static final long serialVersionUID = 1L;
}

class FewVerticesException extends PolygonException {
	FewVerticesException() {
		super("Not enough vertices");
	}
	private static final long serialVersionUID = 1L;
}

class SelfIntersectionException extends PolygonException {
	SelfIntersectionException() {
		super("Polygon have self-intersection(s)");
	}
	private static final long serialVersionUID = 1L;
}
