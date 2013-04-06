package com.example.tyurin.figuresexception;

public class TriangleNotFoundException extends PolygonException {
	public TriangleNotFoundException(String fileName, int number) {
		super("Triangle with number " + number + " not found in file \"" + fileName + "\"");
	}
	private static final long serialVersionUID = 1L;
}
