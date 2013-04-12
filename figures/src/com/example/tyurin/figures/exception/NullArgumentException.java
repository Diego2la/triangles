package com.example.tyurin.figures.exception;

public class NullArgumentException extends PolygonException {
	public NullArgumentException(String argumentName) {
		super(argumentName + "is null");
	}
	private static final long serialVersionUID = 1L;
}
