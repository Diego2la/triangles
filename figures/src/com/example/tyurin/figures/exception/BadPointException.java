package com.example.tyurin.figures.exception;

public class BadPointException extends PolygonException {
	public BadPointException() {
		super("bad point");
	}
	private static final long serialVersionUID = 1L;
}
