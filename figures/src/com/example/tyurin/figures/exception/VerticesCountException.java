package com.example.tyurin.figures.exception;

public class VerticesCountException extends PolygonException {
	public VerticesCountException() {
		super("Wrong vertices count");
	}
	private static final long serialVersionUID = 1L;
}