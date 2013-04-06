package com.example.tyurin.figuresexception;

public class VerticesCountException extends PolygonException {
	public VerticesCountException() {
		super("Wrong vertices count");
	}
	private static final long serialVersionUID = 1L;
}