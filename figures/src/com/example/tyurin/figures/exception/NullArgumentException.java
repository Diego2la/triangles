package com.example.tyurin.figures.exception;

public class NullArgumentException extends PolygonException {
	public NullArgumentException() {
		super("At least one argument is null");
	}
	private static final long serialVersionUID = 1L;
}
