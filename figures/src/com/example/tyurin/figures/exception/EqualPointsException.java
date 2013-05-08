package com.example.tyurin.figures.exception;

public class EqualPointsException extends PolygonException {
	public EqualPointsException() {
		super("points are equal");
	}
	private static final long serialVersionUID = 1L;
}
