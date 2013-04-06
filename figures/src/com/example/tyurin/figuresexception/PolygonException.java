package com.example.tyurin.figuresexception;

public class PolygonException extends Exception
{
	public PolygonException(String error)
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