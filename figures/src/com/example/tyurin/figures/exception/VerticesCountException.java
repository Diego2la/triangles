package com.example.tyurin.figures.exception;

public class VerticesCountException extends PolygonException {
	public VerticesCountException(int count) {
		super("Invalid vertices count = " + count);
	}
	private static final long serialVersionUID = 1L;
}